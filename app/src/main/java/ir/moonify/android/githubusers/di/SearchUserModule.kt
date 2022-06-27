package ir.moonify.android.githubusers.di

import ir.moonify.android.githubusers.baseviewmodel.coroutineDispatcherProvider
import ir.moonify.android.githubusers.data.UsersDataSource
import ir.moonify.android.githubusers.data.UsersRepository
import ir.moonify.android.githubusers.framework.UseCases
import ir.moonify.android.githubusers.framework.network.RemoteUserDataSource
import ir.moonify.android.githubusers.framework.network.Services
import ir.moonify.android.githubusers.presentation.SearchViewModel
import ir.moonify.android.githubusers.usecases.GetUserDetailUseCase
import ir.moonify.android.githubusers.usecases.SearchUserUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    viewModel {
        SearchViewModel(coroutineDispatcherProvider(), get())
    }

    single<Services> { Services.getInstance() }

    single<UsersDataSource> { RemoteUserDataSource((get())) }

    single {
        UsersRepository(usersDataSource = RemoteUserDataSource(get()))
    }
    single {
        UseCases(
            searchUserUseCase = SearchUserUseCase(get()),
            getUserDetailUseCase = GetUserDetailUseCase(get())
        )
    }

}

