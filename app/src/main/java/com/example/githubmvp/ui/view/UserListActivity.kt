package com.example.githubmvp.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmvp.R
import com.example.githubmvp.data.model.User
import com.example.githubmvp.presenter.UserListPresenter
import com.example.githubmvp.ui.adapter.UserAdapter
import com.example.githubmvp.ui.contract.UserListView
import dagger.Component
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject

class UserListActivity : AppCompatActivity(), UserListView {

    private val context = this

    @Inject
    lateinit var userListPresenter: UserListPresenter

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        injectDepedency()

        userListRecycler.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context)
        }

        fetchUserList()
        userListRefresh.setOnRefreshListener {
            fetchUserList()
        }
    }

    private fun injectDepedency() {
    }

    override fun showUserList(users: List<User>) {
        userAdapter.swapData(users)
        Toast.makeText(context, getString(R.string.user_list_update_notification), Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showLoadingState(loadingState: Boolean) {
        userListRefresh.isRefreshing = loadingState
    }

    private fun fetchUserList() {
        userListPresenter.getAllUsers()
    }
}
