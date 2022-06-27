package ir.moonify.android.githubusers.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.moonify.android.githubusers.domain.UserList
import ir.moonify.android.githubusers.baseviewmodel.CoroutineDispatcherProvider
import ir.moonify.android.githubusers.baseviewmodel.GithubUsersViewModel
import ir.moonify.android.githubusers.framework.UseCases
import kotlinx.coroutines.*

class SearchViewModel(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val useCases: UseCases
) : GithubUsersViewModel(
    coroutineDispatcherProvider = coroutineDispatcherProvider
) {

    private val _userList = MutableLiveData<UserList>()
    val userList: LiveData<UserList>
        get() = _userList

    private fun onError(message: String) {
        _errorMessage.postValue(message)
        _loading.postValue(false)
    }

    fun searchUser(username: String) {
        viewModelScope.launch {
            onIO {
                lateinit var response: UserList
                _loading.postValue(true)
                runCatching {
                    response = useCases.searchUserUseCase(username);
                }.onSuccess {
                    _userList.postValue(response)
                    _loading.postValue(false)
                }.onFailure {
                    onError(it.message!!)
                }
            }
        }
    }

}