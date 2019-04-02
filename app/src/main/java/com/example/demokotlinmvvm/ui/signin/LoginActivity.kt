package com.example.demokotlinmvvm.ui.signin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.demokotlinmvvm.R
import com.example.demokotlinmvvm.R.id
import com.example.demokotlinmvvm.base.BaseActivity
import com.example.demokotlinmvvm.base.ViewModelFactory
import com.example.demokotlinmvvm.data.repository.UserRepository
import com.example.demokotlinmvvm.data.source.local.UserLocalDataSource
import com.example.demokotlinmvvm.data.source.local.db.AppDatabase
import com.example.demokotlinmvvm.data.source.remote.UserRemoteDataSource
import com.example.demokotlinmvvm.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            id.buttonLogin -> handleLogin()
            id.textSignUp -> handleSignUp()
        }
    }

    private fun handleSignUp() {
        viewModel.signUp(editUserName.text.toString(), editPassword.text.toString())
    }

    private fun handleLogin() {
        viewModel.login(editUserName.text.toString(), editPassword.text.toString())
    }

    override val layoutId: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        registerObserves()
    }

    private fun registerObserves() {
        viewModel.usersLiveData.observe(this,
            Observer { users ->
                Toast.makeText(this, users?.size.toString(), Toast.LENGTH_SHORT).show()
            })
        viewModel.isLoginSuccess.observe(this, Observer { isSuccess ->
            if (isSuccess!!) {
                Toast.makeText(this, NOTIFY_LOGIN_SUCCESSFULLY, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, NOTIFY_LOGIN_FAILED, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initViews() {
        buttonLogin.setOnClickListener(this)
        textSignUp.setOnClickListener(this)
    }

    override fun obtainViewModel(): LoginViewModel =
        ViewModelProviders.of(
            this,
            ViewModelFactory.getInstance(
                UserRepository
                    .getInstance(
                        UserLocalDataSource(AppDatabase.getInstance(this).userDao()),
                        UserRemoteDataSource()
                    )
            )
        )
            .get(LoginViewModel::class.java)

    companion object {
        val NOTIFY_LOGIN_SUCCESSFULLY: String = "Login successfully"
        val NOTIFY_LOGIN_FAILED: String = "Login failed"
    }

}
