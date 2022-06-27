package ir.moonify.android.githubusers.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.moonify.android.githubusers.databinding.ActivityUserDetailBinding
import ir.moonify.android.githubusers.util.Constants
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = getViewModel()

        intent.getStringExtra(Constants.USER)?.let {
            setTitle("@$it")
            viewModel.getUserDetail(it)
        }

        viewModel.userDetail.observe(this) {
            binding.bioText.text = it.bio
            binding.companyText.text = it.company
            binding.locationText.text = it.location
            binding.blogText.text = it.blog
            binding.emailText.text = it.email
            binding.twitterText.text = it.twitterUsername
            binding.repos.text = it.htmlUrl
            Glide.with(this).load(it.avatarUrl)
                .into(binding.avatar)
        }

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            finish()
        })

    }
}