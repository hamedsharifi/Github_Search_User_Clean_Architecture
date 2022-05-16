package ir.moonify.android.githubusers.presentation

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.moonify.android.githubusers.domain.UserList
import ir.moonify.android.githubusers.framework.BaseViewModel
import ir.moonify.android.githubusers.framework.UseCases
import kotlinx.coroutines.*

class SearchViewModel(application: Application, useCases: UseCases) :
    BaseViewModel(application, useCases) {

    val errorMessage = MutableLiveData<String>()
    val userList = MutableLiveData<UserList>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    fun searchUser(username: String) {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val response = useCases.searchUser(username);
            withContext(Dispatchers.Main) {
                userList.postValue(response)
                loading.value = false
            }
        }
    }

}