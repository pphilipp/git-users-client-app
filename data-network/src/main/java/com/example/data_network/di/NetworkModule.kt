package com.example.data_network.di

import android.content.Context
import com.example.data_network.interceptor.GeneralHeaderInterceptor
import com.example.data_network.interceptor.NetworkConnectionInterceptor
import com.example.data_network.interceptor.UserTokenInterceptor
import com.example.data_network.service.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val SERVER_URL = "https://api.github.com/"
const val USER_TOKEN_LEFT =  "Bearer github_pat_11ACJ72UY0fNVRiRWdH2t3_3yxLg4o3xiH7"
const val USER_TOKEN_RIGHT = "uwCcMEfwtAokr5bTPf5tq0fvhvXG9enZ5OP7CLYGoVfzAma"

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideNetworkConnectionInterceptor(androidContext()) }
    single { provideUserTokenInterceptor("$USER_TOKEN_LEFT$USER_TOKEN_RIGHT") }
    single { provideGeneralHeaderInterceptor() }

    single { provideRetrofit(get(), get()) }
    single { provideMoshi() }
    single { provideDefaultOkhttpClient(get(), get(), get(), get()) }
    single { provideApiService(get()) }
}


fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideNetworkConnectionInterceptor(context: Context) = NetworkConnectionInterceptor(context)
fun provideUserTokenInterceptor(userToken: String) = UserTokenInterceptor(userToken)
fun provideGeneralHeaderInterceptor() = GeneralHeaderInterceptor()

fun provideDefaultOkhttpClient(
    logging: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor,
    userTokenInterceptor: UserTokenInterceptor,
    generalHeaderInterceptor: GeneralHeaderInterceptor,
) = OkHttpClient.Builder().apply {
    connectTimeout(20, TimeUnit.SECONDS)
    readTimeout(20, TimeUnit.SECONDS)
    addInterceptor(logging)
    addInterceptor(networkConnectionInterceptor)
    addInterceptor(userTokenInterceptor)
    addInterceptor(generalHeaderInterceptor)
}.build()


fun provideMoshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

fun provideRetrofit(
    client: OkHttpClient,
    moshi: Moshi,
): Retrofit = Retrofit.Builder()
    .baseUrl(SERVER_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
fun provideApiService(
    retrofit: Retrofit,
): ApiService = retrofit.create(ApiService::class.java)