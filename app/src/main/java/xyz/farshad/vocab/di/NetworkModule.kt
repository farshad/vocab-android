package xyz.farshad.vocab.di

import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.farshad.vocab.BuildConfig
import xyz.farshad.vocab.data.api.RetrofitClient
import xyz.farshad.vocab.data.api.SyncApi

val networkModule = module {
    single { provideRetrofit() }
    single { createApiService<SyncApi>(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient().create()
            )
        )
        .client(RetrofitClient().setInterceptor())
        .build()
}

inline fun <reified T> createApiService(retrofit: Retrofit): T = retrofit.create(T::class.java)