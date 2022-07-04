package com.ez;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *
 */
public class EZWebViewClient extends WebViewClient {
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        view.loadUrl("file:///android_asset/failed.html");
    }
}