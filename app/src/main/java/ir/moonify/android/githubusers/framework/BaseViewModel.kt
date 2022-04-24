package ir.moonify.android.githubusers.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application, protected val useCases: UseCases) :
    AndroidViewModel(application) {

    protected val application: GithubUsersApplication = getApplication()
}