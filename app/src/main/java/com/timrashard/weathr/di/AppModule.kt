package com.timrashard.weathr.di

import com.timrashard.weathr.common.Constant.BASE_URL
import com.timrashard.weathr.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    @Singleton
       TR -> Tüm uygulamada oluşturulan tek örnek kullanılacak bir daha instance oluşturmayacak ---silinebilir
       EN -> The only instance created in the entire application will be used and will not create another instance ---can be deleted
    */

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}