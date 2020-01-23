package xyz.farshad.vocab.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.farshad.vocab.service.util.Constant
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    internal fun provideRetrofitInstance(): Retrofit {
        val gson = GsonBuilder()
                .setLenient().create()
        var constant = Constant()
        return Retrofit.Builder()
                .baseUrl(constant.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}