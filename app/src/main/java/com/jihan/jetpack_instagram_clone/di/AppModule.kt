package com.jihan.jetpack_instagram_clone.di

import com.jihan.jetpack_instagram_clone.domain.repositories.UserRepository
import com.jihan.jetpack_instagram_clone.domain.sources.remote.api.NetworkInterceptor
import com.jihan.jetpack_instagram_clone.domain.sources.remote.api.UserApi
import com.jihan.jetpack_instagram_clone.domain.viewmodels.UserViewmodel
import com.jihan.jetpack_instagram_clone.domain.utils.Constants.BASE_URL
import com.jihan.jetpack_instagram_clone.domain.utils.TokenManager
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {



    // OkHttpClient
    single {
       OkHttpClient.Builder().addInterceptor(NetworkInterceptor(get())).build()
    }

    // Retrofit Builder
    single {
        Retrofit.Builder().client(get()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
    }


    // UserApi
    single {
        get<Retrofit.Builder>().client(get()).build().create(UserApi::class.java)
    }


    // Token manager
    single {
        TokenManager(androidContext())
    }

    // UserRepository
    singleOf(::UserRepository)

    // UserViewmodel
    viewModelOf(::UserViewmodel)


}