package ir.moonify.android.githubusers.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object BaseViewModelFactory : ViewModelProvider.Factory {

    lateinit var application: Application

    lateinit var dependencies: UseCases

    fun inject(application: Application, dependencies: UseCases) {
        BaseViewModelFactory.application = application
        BaseViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, UseCases::class.java)
                .newInstance(
                    application,
                    dependencies
                )
        } else {
            throw IllegalStateException("ViewModel must extend BaseViewModel")
        }
    }

}
