package com.grindewald1900.shereats.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.model.entity.FirebaseBadge
import com.grindewald1900.shereats.utils.ImageUtil
import com.grindewald1900.shereats.utils.firebase.StorageUtil
import com.grindewald1900.shereats.view.custom.RoundImageView


/**
 * Created by Yee on 2021-12-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class BadgeAdapter(var data: List<FirebaseBadge>): RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {
    private lateinit var mContext: Context
    class BadgeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: RoundImageView = view.findViewById(R.id.iv_adapter_badge_logo)
        val tvContent: TextView = view.findViewById(R.id.tv_adapter_badge_content)
        val tvDate: TextView = view.findViewById(R.id.tv_adapter_badge_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_adapter_badge, parent, false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        val dataSlice = data[position]
        holder.tvContent.text = dataSlice.badgeContent
        holder.tvContent.setTextColor(ImageUtil.getBadgeColor(dataSlice.badgeRarity!!))
        holder.tvDate.text = dataSlice.badgeDate
        getBadgeImage(dataSlice.badgeType!!, holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    /**
     * Get image for the Badge
     */
    private fun getBadgeImage(type: Long, view: RoundImageView){
        val childPath = "badge/$type.jpg"
        val pathReference = StorageUtil.reference.child(childPath)
        pathReference.downloadUrl.addOnSuccessListener {
            /**
             * Here we implement the custom target, with call back to listen to the image loading.
             * After resource is loaded, we obtain the resource as a bitmap, and invoke the function {@see setImage()}
             * to refresh the view.
             */
            Glide.with(mContext)
                .asBitmap()
                .load(it.toString())
                .placeholder(R.drawable.ic_baseline_error_outline_24)
                .into(object: CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        view.setImage(resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
        }.addOnFailureListener {
            it.stackTrace
        }
    }
}

