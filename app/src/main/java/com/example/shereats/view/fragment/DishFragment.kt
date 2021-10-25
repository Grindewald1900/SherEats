package com.example.shereats.view.fragment

import android.database.DatabaseUtils
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shereats.R
import com.example.shereats.databinding.DishFragmentBinding

class DishFragment : Fragment() {
    private lateinit var viewModel: DishViewModel

    private lateinit var bingding: DishFragmentBinding

    companion object {
        fun newInstance() = DishFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bingding = DataBindingUtil.inflate(inflater, R.layout.dish_fragment, container, false)
        return bingding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DishViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}