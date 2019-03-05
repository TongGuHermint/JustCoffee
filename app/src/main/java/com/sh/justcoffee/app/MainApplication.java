package com.sh.justcoffee.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.moxie.client.manager.MoxieSDK;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * author： TongGuHermit
 * created on： 2019/2/26 10:50
 */

public class MainApplication extends Application{
	private static LocalBroadcastManager localBroadcastManager;

	@Override
	public void onCreate() {
		super.onCreate();
		//CrashCat.getInstance(getApplicationContext(),null,null).start();
		initCommonUtil();
		initHttpUtil();
//		RPSDK.initialize(this);
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}


	private void initHttpUtil(){
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
		loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
		loggingInterceptor.setColorLevel(Level.INFO);
		builder.addInterceptor(loggingInterceptor)
				.cookieJar(new CookieJarImpl(new SPCookieStore(this)))
				.connectTimeout(10*1000, TimeUnit.MILLISECONDS)
				.hostnameVerifier(new HostnameVerifier() {

					@Override
					public boolean verify(String hostname, SSLSession session) {
						//强行返回true 即验证成功
						return true;
					}
				})
				.proxy(Proxy.NO_PROXY);

		OkGo.getInstance().init(this)
				.setOkHttpClient(builder.build())
				.setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
				.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
				.setRetryCount(5);
	}

	public static LocalBroadcastManager getLocalBroadcastManager(){
		if (localBroadcastManager == null){
			localBroadcastManager = LocalBroadcastManager.getInstance(Utils.getApp());
		}
		return localBroadcastManager;
	}

	private void initCommonUtil(){
		Utils.init(this);
	}
}
