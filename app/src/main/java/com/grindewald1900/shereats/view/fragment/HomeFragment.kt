package com.grindewald1900.shereats.view.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.HomeFragmentBinding
import com.grindewald1900.shereats.model.viewmodel.HomeViewModel
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.view.adapter.RestaurantAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.setAllFirebaseRestaurant()
        viewModel.setState(ConstantUtil.STATE_NULL)
        viewModel.getFirebaseRestaurant().observe(viewLifecycleOwner, Observer {
            binding.rvFragmentHome.adapter = RestaurantAdapter(it)
        })
        viewModel.getState().observe(viewLifecycleOwner) { state ->
            binding.swipeFragmentHome.isRefreshing = false
            setBackGround(state)
        }

        binding.rvFragmentHome.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentHome.itemAnimator = DefaultItemAnimator()
        binding.swipeFragmentHome.setColorSchemeColors(resources.getColor(R.color.colorPrimary),resources.getColor(R.color.google), resources.getColor(R.color.facebook))
        binding.swipeFragmentHome.setOnRefreshListener {
            viewModel.setAllFirebaseRestaurant()
        }
    }

    /**
     * Set background under the recyclerView
     */
    private fun setBackGround(state: Int){
        when(state){
            ConstantUtil.STATE_SUCCESS -> {
                setBackgroundVisibility(View.GONE)
            }
            ConstantUtil.STATE_FAIL -> {
                setBackgroundVisibility(View.VISIBLE)
                binding.ivFragmentHomePlaceholder.setImageResource(R.drawable.ic_baseline_network_check_24)
                binding.tvFragmentHomePlaceholder.setText(R.string.hint_net_error)
            }
            else -> {
                setBackgroundVisibility(View.GONE)
            }
        }
    }

    private fun setBackgroundVisibility(visibility: Int){
        binding.llFragmentHomePlaceholder.visibility = visibility
        binding.ivFragmentHomePlaceholder.visibility = visibility
        binding.tvFragmentHomePlaceholder.visibility = visibility
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(getString(R.string.MainPage), "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(getString(R.string.MainPage), "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(getString(R.string.MainPage), "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e(getString(R.string.MainPage), "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.e(getString(R.string.MainPage), "onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e(getString(R.string.MainPage), "onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(getString(R.string.MainPage), "onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(getString(R.string.MainPage), "onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        Log.e(getString(R.string.MainPage), "onDetach")

    }

}