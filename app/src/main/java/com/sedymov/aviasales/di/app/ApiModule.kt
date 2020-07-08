package com.sedymov.aviasales.di.app

import android.content.Context
import com.sedymov.aviasales.R
import com.sedymov.aviasales.data.net.ApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    @PerApplication
    internal fun apiClient(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)

    @Provides
    @PerApplication
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @PerApplication
    internal fun okHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @PerApplication
    internal fun retrofit(
        context: Context,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(context.getString(R.string.server_url))
            .build()
    }
}