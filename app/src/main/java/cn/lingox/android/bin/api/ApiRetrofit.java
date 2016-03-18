package cn.lingox.android.bin.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import cn.lingox.android.test.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by lipeng21 on 2015/11/9.
 */
public class ApiRetrofit {
    //okhttp
    private final static long connectTimeout = 10 * 1000;
    private final static long readTimeout = 10 * 1000;
    private final static long writeTimeout = 10 * 1000;

    private final static String URL_BASE = "http://www.lingox.cn/";


    private Apis apis;

    ApiRetrofit() {
        OkHttpClient.Builder tBuilder = new OkHttpClient.Builder();
        tBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        tBuilder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        tBuilder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            tBuilder.addInterceptor(interceptor);
        }

        OkHttpClient tClient = tBuilder.build();

        Gson tGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .serializeNulls()
                .create();

        Retrofit tRetrofit = new Retrofit.Builder()
                .client(tClient)
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(tGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        apis = tRetrofit.create(Apis.class);
    }

    public Apis getApis(){
        return apis;
    }
}
