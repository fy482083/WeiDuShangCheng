package example.com.weidushangcheng.model.App;


import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class FrescoApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        getInstace();
    }
    public void getInstace(){
        context = getApplicationContext();

    }

    public static Context getContentInstace(){
        return context;
    }

}
