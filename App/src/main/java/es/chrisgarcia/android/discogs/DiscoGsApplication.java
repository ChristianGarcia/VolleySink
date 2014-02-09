package es.chrisgarcia.android.discogs;

import android.app.Application;

import com.testflightapp.lib.TestFlight;

import es.chrisgarcia.android.discogs.network.manager.RequestManager;

/**
 * {@link android.app.Application} object for the Discogs application
 */
public class DiscoGsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initVolleyRequestManager();
        initTestFlight();

    }

    /**
     * Initializes the TestFlight Manager
     */
    private void initTestFlight() {
        TestFlight.takeOff(this, BuildConfig.TESTFLIGHT_APP_KEY);
    }

    /**
     * Initializes the Volley Request Manager
     */
    private void initVolleyRequestManager() {
        RequestManager.getInstance(getApplicationContext());
    }
}
