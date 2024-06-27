package com.example.pokemon.di

import com.example.pokemon.util.Constants.BASE_URL
import com.example.pokemon.api.PokemonApi
import com.example.pokemon.repository.PokemonMainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokemonApi
    ) = PokemonMainRepository(api)

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
         val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(interceptor)

        return client.build()
    }
    @Singleton
    @Provides
    fun providePokeApi(): PokemonApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .baseUrl(BASE_URL)
            .build()
            .create(PokemonApi::class.java)
    }

}