package com.project.coinretrofit.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.coinretrofit.adapter.CoinAdapter
import com.project.coinretrofit.databinding.FragmentMainBinding
import com.project.coinretrofit.viewmodel.MainViewModel


class MainFragment : Fragment() {
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : MainViewModel
    private val coinAdapter = CoinAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.refreshData()

        binding.coinList.layoutManager = LinearLayoutManager(context)
        binding.coinList.adapter = coinAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.apply {
                coinList.visibility = View.GONE
                coinError.visibility = View.GONE
                coinLoading.visibility = View.VISIBLE
                viewModel.refreshData()
                swipeRefreshLayout.isRefreshing = false

            }


        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.coins.observe(viewLifecycleOwner, Observer { coin ->
            coin?.let {
                binding.coinList.visibility = View.VISIBLE
                coinAdapter.updateCoinList(coin)
            }
        })
        viewModel.coinLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    binding.coinLoading.visibility = View.VISIBLE
                }
                else
                    binding.coinLoading.visibility = View.GONE
            }
        })
        viewModel.coinError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    binding.coinError.visibility = View.VISIBLE
                }
                else
                    binding.coinError.visibility = View.GONE
            }
        })
    }

}