package com.example.shereats.view.activity

import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.databinding.ActivitySearchBinding
import com.example.shereats.model.entity.FirebaseDish
import com.example.shereats.model.viewmodel.SearchViewModel
import com.example.shereats.utils.ToastUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.adapter.RecommendAdapter
import com.example.shereats.view.adapter.SearchAdapter
import com.example.shereats.view.fragment.ViewpagerSearchFragment
import com.example.shereats.view.transformer.ZoomOutPageTransformer

class SearchActivity : FragmentActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var mPager: ViewPager2
    private lateinit var pagerAdapter: FragmentStateAdapter
    // We have 4(actually 3) types of search condition: restaurant name, dish name, cuisine type
    private var searchType: MutableList<Boolean> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        initView()
    }

    // Deal with enter key, start search when clicked
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when(keyCode){
            KeyEvent.KEYCODE_ENTER ->{
                onSearchClicked()
                false
            }
            else -> {
                super.onKeyUp(keyCode, event)
            }
        }
    }

    private fun initView(){
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
//        viewModel.setDishes(20)
//        viewModel.getDishes().observe(this) {
//            binding.rvActivityEventSearch.adapter = SearchAdapter(it)
//            binding.rvActivityEventSearch.layoutManager = LinearLayoutManager(this)
//            binding.rvActivityEventSearch.itemAnimator = DefaultItemAnimator()
//            setViewPager(it)
//        }
        setSearchType()
        binding.btnActivityEventSearchBack.setOnClickListener {
            onBackPressed()
        }
        // By Default, we could search by keywords
        binding.tvActivityEventSearchSearch.setOnClickListener {
            onSearchClicked()
        }
        viewModel.getSearchResult().observe(this) {
            binding.rvActivityEventSearch.adapter = SearchAdapter(it)
        }
    }

    private fun onSearchClicked(){
        val keyword = binding.etActivityEventSearch.text.toString()
        val isTypeAll = !searchType[0] && !searchType[1] && !searchType[2] && !searchType[3]
//        viewModel.setSearchResult(0, keyword, searchType[0], searchType[1], searchType[2], searchType[3], isTypeAll)
    }

    private fun setViewPager(dishes: List<FirebaseDish>){
        // Pager adapter for the recommendation on the top of screen
        pagerAdapter = object: FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return dishes.size
            }
            override fun createFragment(position: Int): Fragment {
                return getFragmentByPosition(position)
            }
            // Create a fragment for viewpager with data
            private fun getFragmentByPosition(position: Int): Fragment{
                return ViewpagerSearchFragment(dishes[position])
            }
        }
        mPager = binding.pagerActivityEventSearch
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(MarginPageTransformer(20))
        mPager.setPageTransformer(ZoomOutPageTransformer())
        mPager.offscreenPageLimit = 5
    }

    private fun setSearchType(){
        setTextViewBackground(binding.tvActivityEventSearchOne,0)
        setTextViewBackground(binding.tvActivityEventSearchTwo,1)
        setTextViewBackground(binding.tvActivityEventSearchThree,2)
        setTextViewBackground(binding.tvActivityEventSearchFour,3)
    }

    /**
     * Set click listener for 4 search conditions
     */
    private fun setTextViewBackground(view: TextView, index: Int){
        searchType.add(index, false)
        view.setOnClickListener {
            if (it.background == null){
                searchType[index] = true
                it.background = AppCompatResources.getDrawable(this, R.drawable.shape_text_view_selected)
            }else{
                it.background = null
                searchType[index] = false
            }
        }
    }



}