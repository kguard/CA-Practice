package com.example.ca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        val viewModel:CoinViewModel by viewModels()
        lifecycleScope.launchWhenStarted {
            viewModel.CoinState.collectLatest {
                when(it.state){
                    State.LOADING->{}
                    State.SUCCESS->{
                        Adapter(it.data){id,rank ->
                            // id를 받아와서 다음생성 프래그먼트로 넘겨주기
                            // Toast.makeText
                        }.also {
                            adapter -> recyclerView.adapter=adapter

                        }
                        recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL))
                        recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                    }
                    State.ERROR->{
                        Toast.makeText(this@MainActivity,it.error,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}