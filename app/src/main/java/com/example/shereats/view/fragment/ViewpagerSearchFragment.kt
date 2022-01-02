package com.example.shereats.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.model.entity.Dish
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.custom.RoundCornerImageView


/**
 * Created by Yee on 2021-12-17.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ViewpagerSearchFragment(var dish: Dish): Fragment() {
    private lateinit var tvTitle: TextView
    private lateinit var ivImage: RoundCornerImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_viewpager_search, container, false)
        tvTitle = view.findViewById(R.id.tv_viewpager_title)
        ivImage = view.findViewById(R.id.iv_viewpager_search)
        tvTitle.text = dish.item_name
        setDishImage(dish.item_id, ivImage)
        return view
    }

    fun setTitle(title: String){
        tvTitle.text = title
    }

    /**
     * Get image for the view pager image
     */
    private fun setDishImage(id: Int, view: ImageView){
        val childPath = "item/$id.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(this)
                .load(it.toString())
                .placeholder(R.drawable.ic_dining_room_48)
                .into(view)
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}