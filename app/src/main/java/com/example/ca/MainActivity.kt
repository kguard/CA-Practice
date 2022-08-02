package com.example.ca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ca.fragment.DetailFragment
import com.example.ca.fragment.ListFragment
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, ListFragment())
            .commit()
        /*supportFragmentManager.commit {
            add(DetailFragment(),null)
            add(ListFragment(),null)
            show(ListFragment())
            hide(DetailFragment())
            replace(R.id.frame,ListFragment())
        }*/

    }

}