package com.example.yjj.demoproj.bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yjj.demoproj.BaseActivity;
import com.example.yjj.demoproj.R;
import com.example.yjj.demoproj.util.ToastUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author:YJJ
 * @date:2016/2/22
 * @email:yangjianjun@117go.com
 */
public class BluetoothChatActivity extends BaseActivity {
    private static final String TAG = BluetoothChatActivity.class.getSimpleName() + "====";
    public static final String ARG_BLUETOOTH_DEVICE = "device";
    private final static UUID MY_UUID = UUID.nameUUIDFromBytes("BluetoothChatActivity".getBytes());
    private final static String NAME = "BluetoothChat";
    private static final int MESSAGE_READ = 0x21;
    private static final int HIDE_DIALOG = 0X22;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mDevice;

    private ConnectThread connectThread;
    private AcceptThread acceptThread;

    private BluetoothSocket mConnectedSocket;
    @Bind(R.id.input_msg)
    EditText input;
    @Bind(R.id.msg)
    TextView message;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mDevice = getIntent().getParcelableExtra(ARG_BLUETOOTH_DEVICE);
        //START LISTENING
        acceptThread = new AcceptThread();
        acceptThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connectedThread != null) {
            connectedThread.cancel();
        }
        if (acceptThread != null) {
            acceptThread.cancel();
        }
        if (connectThread != null) {
            connectThread.cancel();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect:
                if (mConnectedSocket == null || !mConnectedSocket.isConnected()) {
                    connectThread = new ConnectThread(mDevice);
                    connectThread.start();
                    dialog = new ProgressDialog(this);
                    dialog.setMessage("连接中...");
                    dialog.setCancelable(false);
                    dialog.show();
                }
                break;
            case R.id.send:
                if (mConnectedSocket == null) {
                    ToastUtil.toastShortMsg("请先连接");
                    return;
                }
                final String msg = input.getText() == null ? "" : input.getText().toString();
                showMsg(true, msg);
                //send
                connectedThread.write(msg.getBytes());
                break;
        }

    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            mBluetoothAdapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            manageConnectedSocket(mmSocket);
        }

        /**
         * Will cancel an in-progress connection, and close the socket
         */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            } catch (IOException e) {
            }
            mmServerSocket = tmp;
        }

        public void run() {
            Log.d(TAG, "listening...");
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    manageConnectedSocket1(socket);
                    try {
                        mmServerSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        /**
         * Will cancel the listening socket, and cause the thread to finish
         */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
            }
        }
    }


    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
            }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    //connect
    private void manageConnectedSocket(BluetoothSocket socket) {
        Log.d(TAG, "connected");
        if (mConnectedSocket == null) {
            acceptThread.cancel();
            mConnectedSocket = socket;
            connectedThread = new ConnectedThread(mConnectedSocket);
            connectedThread.start();
            mHandler.sendEmptyMessage(HIDE_DIALOG);
        }
    }

    //accept
    private void manageConnectedSocket1(BluetoothSocket socket) {
        Log.d(TAG, "connected1");
        if (mConnectedSocket == null) {
            if (connectedThread != null) connectedThread.cancel();
            mConnectedSocket = socket;
            connectedThread = new ConnectedThread(mConnectedSocket);
            connectedThread.start();
        }
    }

    private ConnectedThread connectedThread;

    static Calendar calendar = Calendar.getInstance();

    private void showMsg(boolean isSend, String msg) {
        calendar.setTime(new Date());
        String name = "\nSend:";
        if (!isSend) name = "\nReceive:";
        message.append(name + msg + "    " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
    }

    private void hideDialog() {
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_READ:
                    showMsg(false, new String((byte[]) msg.obj));
                    break;
                case HIDE_DIALOG:
                    hideDialog();
            }
        }
    };
}
