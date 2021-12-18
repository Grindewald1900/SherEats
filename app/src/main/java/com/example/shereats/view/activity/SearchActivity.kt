package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.shereats.R
import com.example.shereats.databinding.ActivitySearchBinding
import com.example.shereats.model.viewmodel.SearchViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.view.fragment.ViewpagerSearchFragment
import com.example.shereats.view.transformer.ZoomOutPageTransformer

class SearchActivity : FragmentActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var mPager: ViewPager2
    private lateinit var pagerAdapter: FragmentStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        initView()
    }

    private fun initView(){
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        // Pager adapter for the recommendation on the top of screen
        pagerAdapter = object: FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return ConstantUtil.MAX_RECOMMEND_ITEM
            }
            override fun createFragment(position: Int): Fragment {
                return getFragmentByPosition(position)
            }

            private fun getFragmentByPosition(position: Int): Fragment{
                return ViewpagerSearchFragment()
            }
        }

        mPager = binding.pagerActivityEventSearch
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(MarginPageTransformer(20))
        mPager.setPageTransformer(ZoomOutPageTransformer())
        mPager.offscreenPageLimit = 5

    }
}