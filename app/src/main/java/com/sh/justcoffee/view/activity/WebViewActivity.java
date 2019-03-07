package com.sh.justcoffee.view.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sh.justcoffee.R;
import com.sh.justcoffee.widget.WebViewManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


	@BindView(R.id.web)
	WebView mWebView;

	@Override
	protected int getContentView() {
		return R.layout.activity_web_view;
	}

	@Override
	protected void initViews() {
		WebViewManager manager = new WebViewManager(mWebView);
		manager.enableAdaptive();
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setBlockNetworkImage(false);
		//启动webview的h5的本地存储功能
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setAppCacheMaxSize(1024*1024*8);
		String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
		mWebView.getSettings().setAppCachePath(appCachePath);
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.getSettings().setAppCacheEnabled(true);

//		mWebView.getSettings().setSupportZoom(true);
//		mWebView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
		mWebView.loadUrl("http://frontend_skynet.xkw66.com/");

		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;
			}
			@Override
			public void onReceivedSslError(WebView view,
										   SslErrorHandler handler, SslError error) {
				handler.proceed();// 接受所有网站的证书
			}

		});
	}

}
