package ir.moonify.android.githubusers.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonify.android.githubusers.R
import com.moonify.android.githubusers.databinding.ActivitySearchBinding
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate
import ir.moonify.android.githubusers.domain.User
import ir.moonify.android.githubusers.framework.BaseViewModelFactory


class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: UsersRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, BaseViewModelFactory).get(SearchViewModel::class.java)
        initRecyclerView()
        with(binding.persistentSearchView) {
            setOnLeftBtnClickListener {

            }
            setOnClearInputBtnClickListener {
                binding.userList.adapter = null
                binding.emptyMessage.visibility = View.VISIBLE
            }
            setVoiceRecognitionDelegate(VoiceRecognitionDelegate(this@SearchActivity))
            setOnSearchConfirmedListener { searchView, query ->
                binding.userList.adapter = null
                viewModel.searchUser(query)
                searchView.collapse()
            }
            setSuggestionsDisabled(true)
        }

        viewModel.userList.observe(this, Observer {
            binding.userList.adapter = getAdapter(it.items)
            binding.emptyMessage.visibility = View.GONE
        })

        viewModel.loading.observe(this, Observer {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            binding.emptyMessage.visibility = View.GONE
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }

    private fun initRecyclerView() = with(binding.userList) {
        layoutManager = initLayoutManager()
    }

    private fun initLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(this)
    }

    private fun getAdapter(users: MutableList<User>): UsersRecyclerViewAdapter {
        return UsersRecyclerViewAdapter(this, users)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        VoiceRecognitionDelegate.handleResult(binding.persistentSearchView, requestCode, resultCode, data)
    }
}