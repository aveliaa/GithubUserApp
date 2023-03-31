package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()

        mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        activityMainBinding.apply {
            listUser.layoutManager = LinearLayoutManager(this@MainActivity)
            listUser.setHasFixedSize(true)
            listUser.adapter = userAdapter

            searchIconButton.setOnClickListener {
                search()
            }

            searchField.setOnKeyListener { view, i, keyEvent ->
                if(i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN){
                    search()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false

            }
        }

        mainViewModel.getSearchResult().observe(this,{
            if(it!=null){
                userAdapter.setListUser(it)
                setProgressBar(false)
            }
        })
    }

    private fun search(){
        activityMainBinding.apply {
            val input = searchField.text.toString()
            if(input.isNotEmpty()){
                setProgressBar(true)
                mainViewModel.setSearchResult(input)
            }
        }
    }

    // visual effect : loading progress bar
    private fun setProgressBar(state: Boolean){
        var status = View.GONE
        if(state){
            status = View.VISIBLE
        }
        activityMainBinding.progress.visibility = status
    }
}