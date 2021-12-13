package com.example.shereats.view.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shereats.R

class UserInfoFragment : Fragment() {

    private lateinit var homeViewModel: MHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(MHomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }
}