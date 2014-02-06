package chris.volleysink;

import android.app.Application;

import chris.volleysink.volley.RequestManager;

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
