package com.example.yjj.demoproj;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.yjj.demoproj.jsbridge.JSInterface;

/**
 * @author:YJJ
 * @date:2016/1/27
 * @email:yangjianjun@117go.com
 */
public class WebViewActivity extends BaseActivity {
    private WebView webView;
    private static final String HTML = "<!DOCTYPE html><html>\n" +
            "<head>" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />" +
            "<link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" />" +
            "</head>" +
            "<body>" +
            "<input type='button' value='Say Something' style = \"width:200px;height:60px\" onclick = \"showInfo('hello')\"/>" +
            "<script type=\"text/javascript\">" +
            "    function showInfo(info){" +
            "        Android.showInfo(info);}" +
            "function nativeCallJs(){" +
            " document.getElementById(\"content\").innerHTML += \"native call js with arg\";" +
            "}" +
            "</script>" +
            "<div id=\"content\">content</div>" +
            "</body></html>";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        webView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSInterface(), "Android");
        webView.loadData(HTML, "text/html", null);
        webView.loadUrl("javascript:nativeCallJs()");
    }
}
