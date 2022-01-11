package com.grindewald1900.shereats.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.view.custom.RoundCornerImageView

class ViewpagerTheme(var imageId: Int, var themeId: Int): Fragment() {
    private lateinit var ivTheme: RoundCornerImageView
    private lateinit var ivCheck: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedTheme = requireContext().getSharedPreferences(ConstantUtil.SHARED_THEME, Context.MODE_PRIVATE)
        val view = inflater.inflate(R.layout.view_viewpager_theme, container, false)
        ivTheme = view.findViewById(R.id.iv_viewpager_theme)
        ivCheck = view.findViewById(R.id.iv_viewpager_theme_check)
        ivTheme.setImageResource(imageId)
        ivTheme.setOnClickListener {
            ivCheck.setImageResource(R.drawable.ic_iconmonstr_checkbox_select)
            sharedTheme.edit().putString(ConstantUtil.CURRENT_THEME, themeId.toString()).apply()
        }
        return view
    }
}