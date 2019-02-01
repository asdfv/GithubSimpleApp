package by.grodno.vasili.githubsimpleapp;

import android.app.Application;

import timber.log.Timber;

public class GithubSimpleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
