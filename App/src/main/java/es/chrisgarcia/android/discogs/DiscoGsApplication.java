package es.chrisgarcia.android.discogs;

import android.app.Application;

import es.chrisgarcia.android.discogs.network.manager.RequestManager;

/**
 * Created by Christian on 2/6/14.
 */
public class DiscoGsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // RequestManager initialization
        RequestManager.getInstance(getApplicationContext());
    }
}
