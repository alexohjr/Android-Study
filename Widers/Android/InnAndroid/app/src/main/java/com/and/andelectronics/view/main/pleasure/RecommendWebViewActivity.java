package com.and.andelectronics.view.main.pleasure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.and.andelectronics.Constants;
import com.and.andelectronics.R;
import com.and.andelectronics.common.ez.EZAlertDialog;
import com.ez.EZActivity;
import com.ez.EZWebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecommendWebViewActivity extends AppCompatActivity {


    private String TAG = RecommendWebViewActivity.class.getName();
    // ui
    @Bind(R.id.recommendWebView)
    WebView _webView;

    // model
    @NonNull
    private String _defaultUrl;

    @SuppressLint("JavascriptInterface")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String title = i.getStringExtra(Constants.TITLE);
        _defaultUrl  = i.getStringExtra(Constants.URL);

        if (_defaultUrl.lastIndexOf(".pdf") > 0) {
            _defaultUrl = "https://docs.google.com/gview?embedded=true&url=" + _defaultUrl;
        }

        //setCustomActionBar(title); // 액션바 타이틀 적용
        Log.d(TAG, "[WebActivity] onCreated > title->" + title);
        Log.d(TAG, "[WebActivity] onCreated > url->" + _defaultUrl);

        _webView.getSettings().setJavaScriptEnabled(true);
        // _webView.clearCache(true);
        // _webView.clearHistory();
        _webView.setWebViewClient(new WebViewClient());

        // 자바스크립트 인터페이스 등록
        _webView.addJavascriptInterface(new CustomJavascriptInterface(), "JSInterface");
        _webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                EZAlertDialog.show1(getSupportFragmentManager(), message, new EZAlertDialog.EZAlertDialogListener() {
                    @Override
                    public void onClickConfirm(int selected) {

                    }

                    @Override
                    public void onClickConfirm() {
                        result.confirm();
                    }

                    @Override
                    public void onClickCancel() {

                    }
                }, null, getString(R.string.confirm));
                return true;
            }
        });
        //.자바스크립트 인터페이스 등록
    }

    @Override
    public void onResume() {
        super.onResume();
        _webView.loadUrl(_defaultUrl);
    }

    /**
     * 로딩 다이얼로그를 위한 커스텀 웹뷰 다이얼로그
     */
    private class WebViewClient extends EZWebViewClient {
        public boolean shouldOverrideUrlLoading (WebView view, String url) {
            Log.d(TAG, "[WebViewClient] shouldOverrideUrlLoading()  url->"+url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "[WebViewClient] onPageStarted()  url->" + url);
            //showProgress();
        }

        public void onPageFinished (WebView view, String url) {
            Log.d(TAG, "[WebViewClient] onPageFinished() > url->" + url);
            //dismissProgress();
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.d(TAG, "onReceivedError()");
            //dismissProgress();
        }
    }

    /**
     * 자바스크립트 인터페이스
     * 웹과 네이티브 인터페이스 역할을 한다.
     */
    class CustomJavascriptInterface {
        @JavascriptInterface
        public void log(String s) {
            Log.d(TAG, "Javascript Log->" + s);
        }

        @JavascriptInterface
        public void finish() {
            Log.d(TAG, "finish() called from web.");
            RecommendWebViewActivity.this.finish();
        }
    }
}
