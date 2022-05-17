package ir.moonify.android.githubusers.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.moonify.android.githubusers.domain.UserDetail
import ir.moonify.android.githubusers.framework.BaseViewModel
import ir.moonify.android.githubusers.framework.UseCases
import kotlinx.coroutines.*

class UserDetailViewModel(application: Application, useCases: UseCases) :
    BaseViewModel(application, useCases) {

    val errorMessage = MutableLiveData<String>()
    val userDetail = MutableLiveData<UserDetail>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    fun getUserDetail(username: String) {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val response = useCases.getUserDetail(username)
            withContext(Dispatchers.Main) {
                userDetail.postValue(response)
                loading.value = false
            }
        }
    }

}