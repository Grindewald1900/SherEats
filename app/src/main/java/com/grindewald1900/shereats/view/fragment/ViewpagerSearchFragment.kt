package com.grindewald1900.shereats.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseDish
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.firebase.StorageUtil
import com.grindewald1900.shereats.view.activity.DetailDishActivity
import com.grindewald1900.shereats.view.custom.RoundCornerImageView


/**
 * Created by Yee on 2021-12-17.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ViewpagerSearchFragment(var dish: FirebaseDish): Fragment() {
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
        tvTitle.text = dish.itemName
        setDishImage(dish.itemId!!, ivImage)
        view.setOnClickListener {
            val intent = Intent(context, DetailDishActivity::class.java)
            intent.putExtra(ConstantUtil.ENTITY_DISH, dish)
            context?.startActivity(intent)
        }
        return view
    }

    fun setTitle(title: String){
        tvTitle.text = title
    }

    /**
     * Get image for the view pager image
     */
    private fun setDishImage(id: Long, view: ImageView){
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