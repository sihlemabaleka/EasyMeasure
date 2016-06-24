package com.easymeasure;

import android.app.Application;

import com.digits.sdk.android.Digits;
import com.easymeasure.model.BaseClothingMeasurements;
import com.easymeasure.model.Client;
import com.easymeasure.model.ClothesBedding;
import com.easymeasure.model.KidsMeasurement;
import com.easymeasure.model.Linen;
import com.easymeasure.model.MenMeasurements;
import com.easymeasure.model.Order;
import com.easymeasure.model.WomenMeasurements;
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
        ParseObject.registerSubclass(BaseClothingMeasurements.class);
        ParseObject.registerSubclass(ClothesBedding.class);
        ParseObject.registerSubclass(KidsMeasurement.class);
        ParseObject.registerSubclass(Linen.class);
        ParseObject.registerSubclass(MenMeasurements.class);
        ParseObject.registerSubclass(WomenMeasurements.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "7LafR9BmK4JhG4vTpepN15ZVCbVKZhZs9kv1KFLZ", "By51BeMIknxNFb9pSh6IfFPX0uC7kfUriXQJM2Ta");

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
    }
}
