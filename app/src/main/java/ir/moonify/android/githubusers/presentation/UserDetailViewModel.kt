package ir.moonify.android.githubusers.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.moonify.android.githubusers.domain.UserDetail
import ir.moonify.android.githubusers.baseviewmodel.CoroutineDispatcherProvider
import ir.moonify.android.githubusers.baseviewmodel.GithubUsersViewModel
import ir.moonify.android.githubusers.domain.UserList
import ir.moonify.android.githubusers.framework.UseCases
import kotlinx.coroutines.*

class UserDetailViewModel(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val useCases: UseCases
) : GithubUsersViewModel(
    coroutineDispatcherProvider = coroutineDispatcherProvider
) {

    private val _userDetail = MutableLiveData<UserDetail>()
    val userDetail: LiveData<UserDetail>
        get() = _userDetail

    private fun onError(message: String) {
        _errorMessage.postValue(message)
        _loading.postValue(false)
    }

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            onIO {
                lateinit var response: UserDetail
                _loading.postValue(true)
                runCatching {
                    response = useCases.getUserDetailUseCase(username);
                }.onSuccess {
                    _userDetail.postValue(response)
                    _loading.postValue(false)
                }.onFailure {
                    onError(it.message!!)
                }
            }
        }
    }

}