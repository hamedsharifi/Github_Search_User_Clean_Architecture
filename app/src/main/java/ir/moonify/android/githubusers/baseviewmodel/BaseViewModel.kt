package ir.moonify.android.githubusers.baseviewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    protected val coroutineContexts: CoroutineDispatcherProvider
) : ViewModel(), CoroutineScope {

    protected val job = SupervisorJob()

    protected val scope = CoroutineScope(mainDispatcher() + job)

    fun bgDispatcher(): CoroutineDispatcher {
        return coroutineContexts.bgDispatcher()
    }

    fun mainDispatcher(): CoroutineDispatcher {
        return coroutineContexts.uiDispatcher()
    }

    fun immediateDispatcher(): CoroutineDispatcher {
        return coroutineContexts.immediateDispatcher()
    }

    fun ioDispatcher(): CoroutineDispatcher {
        return coroutineContexts.ioDispatcher()
    }

    suspend inline fun <T> onBg(crossinline coroutine: suspend () -> T): T {
        return withContext(ioDispatcher()) {
            coroutine()
        }
    }

    suspend inline fun <T> onIO(crossinline coroutine: suspend () -> T): T {
        return withContext(ioDispatcher()) {
            coroutine()
        }
    }

    suspend inline fun <T> onUI(crossinline coroutine: suspend () -> T): T {
        return withContext(mainDispatcher()) {
            coroutine()
        }
    }

    suspend inline fun <T> onUIImmediate(crossinline coroutine: suspend () -> T): T {
        return withContext(immediateDispatcher()) {
            coroutine()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = scope.coroutineContext

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }

}