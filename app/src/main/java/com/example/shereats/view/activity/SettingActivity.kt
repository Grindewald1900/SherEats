package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.shereats.R
import com.example.shereats.view.fragment.ViewpagerTheme
import com.example.shereats.databinding.ActivitySettingBinding
import com.example.shereats.view.transformer.ZoomOutPageTransformer

class SettingActivity : BaseActivityNoBar() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        initView()
    }

    private fun initView(){
        initViewpager()
    }

    private fun initViewpager(){
        val mPager = binding.viewpagerActivitySettingTheme
        val themes: MutableList<String> = mutableListOf("theme_1", "theme_2", "theme_3", "theme_4", "theme_5")
        val pagerAdapter = object: FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return themes.size
            }

            override fun createFragment(position: Int): Fragment {
                val id = resources.getIdentifier(themes[position], "drawable", packageName)
                return ViewpagerTheme(id, position + 1)
            }
        }
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(MarginPageTransformer(20))
        mPager.setPageTransformer(ZoomOutPageTransformer())
        mPager.offscreenPageLimit = 3

    }
}