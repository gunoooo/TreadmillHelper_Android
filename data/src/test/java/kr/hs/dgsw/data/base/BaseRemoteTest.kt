package kr.hs.dgsw.data.base

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kr.hs.dgsw.data.etc.Object
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

open class BaseRemoteTest {
    protected lateinit var retrofit: Retrofit

    @Before
    fun before() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val errorResponseInterceptor = ErrorResponseInterceptor()
        val okHttpBuilder = OkHttpClient().newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
//            .addInterceptor(errorResponseInterceptor)
        retrofit = Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(Object.DEFAULT_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}