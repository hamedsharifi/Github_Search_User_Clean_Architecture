package ir.moonify.android.githubusers.di

import ir.moonify.android.githubusers.baseviewmodel.coroutineDispatcherProvider
import ir.moonify.android.githubusers.data.UsersDataSource
import ir.moonify.android.githubusers.data.UsersRepository
import ir.moonify.android.githubusers.framework.UseCases
import ir.moonify.android.githubusers.framework.network.RemoteUserDataSource
import ir.moonify.android.githubusers.framework.network.Services
import ir.moonify.android.githubusers.presentation.SearchViewModel
import ir.moonify.android.githubusers.presentation.UserDetailViewModel
import ir.moonify.android.githubusers.usecases.GetUserDetailUseCase
import ir.moonify.android.githubusers.usecases.SearchUserUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val getUserDetailModule = module {

    viewModel {
        UserDetailViewModel(coroutineDispatcherProvider(), get())
    }

}


