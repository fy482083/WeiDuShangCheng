package example.com.weidushangcheng.model.httputils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import example.com.weidushangcheng.model.App.FrescoApp;
import example.com.weidushangcheng.model.api.API;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public final API api;

    public RetrofitUtils(){
        //拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Logg())
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://mobile.bwstudent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        api = retrofit.create(API.class);
    }
   //拦截器
    private class Logg implements Interceptor {
      SharedPreferences wenjian = FrescoApp.getContentInstace().getSharedPreferences("sp", Context.MODE_PRIVATE);
       @Override
       public Response intercept(Chain chain) throws IOException {
           Request request = chain.request();
           Request.Builder header = request.newBuilder()
                   .addHeader("userId", wenjian.getString("userId",""))
                   .addHeader("sessionId", wenjian.getString("sessionId",""));
           Request build = header.build();
           return chain.proceed(build);
       }

   }

    //静态内部类
    public static RetrofitUtils getInstance(){
       return HttpGetInstance.retrofitUtils;
    }
    static class HttpGetInstance{
         private static RetrofitUtils retrofitUtils = new RetrofitUtils();
    }
}
