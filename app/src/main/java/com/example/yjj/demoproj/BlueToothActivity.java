package com.example.yjj.demoproj;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yjj.demoproj.bluetooth.BluetoothChatActivity;
import com.example.yjj.demoproj.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:YJJ
 * @date:2016/2/22
 * @email:yangjianjun@117go.com
 */
public class BlueToothActivity extends BaseActivity {
    private ListView listView;
    private ArrayAdapter<String> mArrayAdapter;
    private List<BluetoothDevice> devices = new ArrayList();
    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());

                devices.add(device);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(listView);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
        if (bluetoothAdapter != null) {
            mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
            listView.setAdapter(mArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //聊天
                    Intent intent = new Intent(BlueToothActivity.this, BluetoothChatActivity.class);
                    intent.putExtra(BluetoothChatActivity.ARG_BLUETOOTH_DEVICE, devices.get(position));
                    startActivity(intent);
                }
            });
            if (!bluetoothAdapter.startDiscovery())
                ToastUtil.toastShortMsg("搜索失败");
        } else {
            ToastUtil.toastShortMsg("该设备不支持蓝牙");
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
