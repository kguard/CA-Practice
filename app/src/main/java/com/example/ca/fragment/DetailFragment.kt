package com.example.ca.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ca.R
import com.example.ca.State
import com.example.ca.viewmodel.CoinDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment: Fragment() {

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
        val inflate = inflater.inflate(R.layout.fragment_detail, container, false)
        val viewModel: CoinDetailViewModel by viewModels()
        
        setFragmentResultListener("id")
        { requestKey, bundle -> val result = bundle.getString("bundleKey")
            viewModel.getCoinDetail(result.toString()) }

        var id=inflate.findViewById<TextView>(R.id.detailid)
        var name=inflate.findViewById<TextView>(R.id.detailname)
        var descrption=inflate.findViewById<TextView>(R.id.detaildescription)
        var symbol=inflate.findViewById<TextView>(R.id.detailsymbol)
        var rank=inflate.findViewById<TextView>(R.id.detailrank)
        var isActive=inflate.findViewById<TextView>(R.id.detailactive)
        var tag=inflate.findViewById<TextView>(R.id.detailtag)
        var team=inflate.findViewById<TextView>(R.id.detailteam)

        lifecycleScope.launchWhenStarted {
            viewModel.coinDetailState.collectLatest {
                when(it.state){
                    State.LOADING->{}
                    State.SUCCESS->{
                        id.text= it.detail!!.coinId
                        name.text=it.detail.name
                        descrption.text=it.detail.description
                        symbol.text="("+it.detail.symbol+")"
                        rank.text= it.detail.rank.toString()
                        if(it.detail.isActive==true)
                        {
                            isActive.text="active"
                            isActive.setTextColor(Color.GREEN)
                        }
                        else
                        {
                            isActive.text="inactive"
                            isActive.setTextColor(Color.RED)
                        }
                        tag.text= it.detail.tags.toString()
                        var listname=it.detail.team.map{"\n"+it.name+" - "+it.position}.toString()
                        team.text=listname


                    }
                    State.ERROR -> {
                        Toast.makeText(context,it.error,Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}