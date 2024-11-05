package com.jihan.jetpack_instagram_clone.di

import com.jihan.jetpack_instagram_clone.domain.repositories.UserRepository
import com.jihan.jetpack_instagram_clone.domain.sources.remote.api.NetworkInterceptor
import com.jihan.jetpack_instagram_clone.domain.sources.remote.api.UserApi
import com.jihan.jetpack_instagram_clone.domain.viewmodels.UserViewmodel
import com.jihan.jetpack_instagram_clone.domain.utils.Constants.BASE_URL
import com.jihan.jetpack_instagram_clone.domain.utils.TokenManager
import com.jihan.jetpack_instagram_clone.domain.viewmodels.NavigatorViewmodel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {




    single {
       OkHttpClient.Builder().addInterceptor(NetworkInterceptor(get())).build()
    }  //! OkHttpClient


    single {
        Retrofit.Builder().client(get()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
    } //! Retrofit Builder



    single {
        get<Retrofit.Builder>().client(get()).build().create(UserApi::class.java)
    }  //! UserApi



    single {
        TokenManager(androidContext())
    }  //! Token manager


    single { NavigatorViewmodel() } //! NavigatorViewmodel


    singleOf(::UserRepository)  //! UserRepository


    viewModelOf(::UserViewmodel) //! UserViewmodel


}