package com.easymeasure;

import android.app.Application;

import com.digits.sdk.android.Digits;
import com.easymeasure.model.Client;
import com.easymeasure.model.ClothesBedding;
import com.easymeasure.model.Order;
import com.parse.Parse;
import com.parse.ParseObject;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

public class AppController extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "CTlwywiCe3F5FetNUcVGXGYNj";
    private static final String TWITTER_SECRET = "wmi1doMJt4vCGZWEsKUdCDZ8OIfjCaBgxqqBEmSLmGT3PHQTfq";

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Order.class);
        ParseObject.registerSubclass(Client.class);
        ParseObject.registerSubclass(ClothesBedding.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .server("https://appriseserver.appspot.com7/parse/")
                .applicationId("HgYoX4cEmfApkwE374pxEt5tAiPN9XEHeBvV3403")
                .enableLocalDataStore()
                .build());

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
    }
}
