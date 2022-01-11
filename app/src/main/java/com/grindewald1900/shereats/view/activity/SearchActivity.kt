package com.grindewald1900.shereats.view.activity

import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.ActivitySearchBinding
import com.grindewald1900.shereats.model.entity.FirebaseDish
import com.grindewald1900.shereats.model.viewmodel.SearchViewModel
import com.grindewald1900.shereats.view.adapter.SearchAdapter
import com.grindewald1900.shereats.view.fragment.ViewpagerSearchFragment
import com.grindewald1900.shereats.view.transformer.ZoomOutPageTransformer

class SearchActivity : BaseActivityNoBar() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var mPager: ViewPager2
    private lateinit var pagerAdapter: FragmentStateAdapter
    private var isViewPagerInit = false
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
        viewModel.setAllDishes()
        viewModel.getDishes().observe(this) {
            binding.rvActivityEventSearch.adapter = SearchAdapter(it)
            binding.rvActivityEventSearch.layoutManager = LinearLayoutManager(this)
            binding.rvActivityEventSearch.itemAnimator = DefaultItemAnimator()
            if(!isViewPagerInit){
                setViewPager(it)
                isViewPagerInit = true
            }
        }
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
        if(isTypeAll){
            viewModel.setSearchResult(keyword, true, true, true, true)
        }else{
            viewModel.setSearchResult(keyword, searchType[0], searchType[1], searchType[2], searchType[3])
        }
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