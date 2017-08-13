package com.example.stormbaron.agank.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.model.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class WebViewActivity extends BaseActivity {
    /**
     * 页面元素
     */
    private WebView mWebView;
    private FloatingActionButton mCollectBtn;

    private String url;
    private static String KEY_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        url = getIntent().getStringExtra(KEY_URL);
        initView();
        EventBus.getDefault().post(new MessageEvent());
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.id_webview_content);
        mCollectBtn=(FloatingActionButton) findViewById(R.id.id_gank_collect);
        mWebView.loadUrl(url);
    }

    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);

    }
}
