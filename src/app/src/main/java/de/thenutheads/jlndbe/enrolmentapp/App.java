package de.thenutheads.jlndbe.enrolmentapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jlndbe on 15.09.2015.
 */
public class App extends Application {

    private static Context _context;

    @Override
    public void onCreate(){
        super.onCreate();
        _context = this;
    }

    public static Context getContext(){
            return _context;
    }
}
