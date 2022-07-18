package com.example.ca.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ca.Adapter
import com.example.ca.R
import com.example.ca.State
import com.example.ca.viewmodel.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_list, container, false)
        val viewModel: CoinListViewModel by viewModels()
        var recyclerView=inflate.findViewById<RecyclerView>(R.id.recyclerview)

        lifecycleScope.launchWhenStarted {
            viewModel.CoinState.collectLatest {
                when(it.state){
                    State.LOADING->{}
                    State.SUCCESS->{
                        Adapter(it.data)
                        { id ->
                            setFragmentResult("id", bundleOf("bundleKey" to id))
                            parentFragmentManager.beginTransaction().replace(R.id.frame,DetailFragment()).addToBackStack(null).commit()
                            // id를 받아와서 다음생성 프래그먼트로 넘겨주기
                            // Toast.makeText
                        }
                            .also {
                                    adapter -> recyclerView.adapter=adapter
                            }
                        recyclerView.addItemDecoration(
                            DividerItemDecoration(context,
                                DividerItemDecoration.VERTICAL)
                        )
                        recyclerView.layoutManager= LinearLayoutManager(context)
                    }
                    State.ERROR->{
                        Toast.makeText(context,it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return inflate
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}