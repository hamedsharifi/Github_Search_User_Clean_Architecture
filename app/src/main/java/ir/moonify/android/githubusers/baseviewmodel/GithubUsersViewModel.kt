package ir.moonify.android.githubusers.baseviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class GithubUsersViewModel(
    coroutineDispatcherProvider: CoroutineDispatcherProvider = coroutineDispatcherProvider()
) : BaseViewModel(coroutineDispatcherProvider) {

    open val _errorMessage = MutableLiveData<String>()
    open val errorMessage: LiveData<String>
        get() = _errorMessage

    open val _loading = MutableLiveData<Boolean>()
    open val loading: LiveData<Boolean>
        get() = _loading
}


fun coroutineDispatcherProvider() = object : CoroutineDispatcherProvider {

    override fun bgDispatcher(): CoroutineDispatcher = Dispatchers.Default

    override fun uiDispatcher(): CoroutineDispatcher = Dispatchers.Main

    override fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    override fun immediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

}