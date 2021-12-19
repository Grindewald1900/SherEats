package com.example.shereats.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.shereats.R
import com.example.shereats.model.entity.Dish


/**
 * Created by Yee on 2021-12-17.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ViewpagerSearchFragment(var dish: Dish): Fragment() {
    private lateinit var tvTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_viewpager_search, container, false)
        tvTitle = view.findViewById(R.id.tv_viewpager_title)
        tvTitle.text = dish.item_name
        return view
    }

    fun setTitle(title: String){
        tvTitle.text = title
    }
}