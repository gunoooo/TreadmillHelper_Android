package kr.hs.dgsw.data.base

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kr.hs.dgsw.domain.etc.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

open class BaseRemoteTest {
    protected lateinit var retrofit: Retrofit

    @Before
    fun before() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val errorResponseInterceptor = ErrorResponseInterceptor()
        val okHttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
//            .addInterceptor(errorResponseInterceptor)
        retrofit = Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(Constants.DEFAULT_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}