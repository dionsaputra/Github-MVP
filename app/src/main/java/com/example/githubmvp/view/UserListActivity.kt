package com.example.githubmvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubmvp.R
import com.example.githubmvp.adapter.UserAdapter
import com.example.githubmvp.contract.UserListView
import com.example.githubmvp.model.User
import com.example.githubmvp.presenter.UserListPresenter
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity(), UserListView {

    private val context = this
    private val TAG = UserListActivity::class.java.simpleName

    private val userListPresenter = UserListPresenter(this)
    private val userAdapter = UserAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userListRecycler.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context)
        }

        fetchUserList()
        userListRefresh.setOnRefreshListener {
            fetchUserList()
        }
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

    fun fetchUserList() {
        userListPresenter.getAllUsers()
    }
}
