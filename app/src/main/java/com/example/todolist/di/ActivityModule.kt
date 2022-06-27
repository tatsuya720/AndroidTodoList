package com.example.todolist.di

import android.content.Context
import com.example.navigator.Navigator
import com.example.todolist.common.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindNavigator(
        navigatorImpl: NavigatorImpl
    ): Navigator

}
