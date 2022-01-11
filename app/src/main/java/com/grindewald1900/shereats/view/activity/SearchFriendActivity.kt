package com.grindewald1900.shereats.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.ActivitySearchFriendBinding
import com.grindewald1900.shereats.model.entity.FirebaseUser
import com.grindewald1900.shereats.model.viewmodel.SearchFriendViewModel
import com.grindewald1900.shereats.utils.ToastUtil
import com.grindewald1900.shereats.view.adapter.UserAdapter
import com.grindewald1900.shereats.view.fragment.ViewpagerSearchFriendFragment
import com.grindewald1900.shereats.view.transformer.ZoomOutPageTransformer

class SearchFriendActivity : BaseActivityNoBar() {
    private lateinit var binding: ActivitySearchFriendBinding
    private lateinit var viewModel: SearchFriendViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_friend)
        initView()
    }

    private fun initView(){
        viewModel = ViewModelProvider(this).get(SearchFriendViewModel::class.java)
        viewModel.setFriends()
        viewModel.getRecFriend().observe(this){
            initViewPager(it)
        }
        viewModel.getSearchFriend().observe(this){
            initRecyclerView(it)
        }
        binding.tvActivityEventSearchSearchFriend.setOnClickListener {
            val keyword = binding.etActivityEventSearchFriend.text
            if(keyword.isNullOrBlank()){
                ToastUtil.showShortMessage(R.string.hint_no_name, this)
            }else{
                viewModel.setSearchFriends(keyword.toString())
            }
        }
    }

    private fun initRecyclerView(friends: List<FirebaseUser>){
        val recyclerView = binding.rvActivityEventSearchFriend
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter(friends)
        }
    }

    private fun initViewPager(friends: List<FirebaseUser>){
        val pagerAdapter: FragmentStateAdapter = object: FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return friends.size
            }

            override fun createFragment(position: Int): Fragment {
                return ViewpagerSearchFriendFragment(friends[position])
            }
        }
        binding.pagerActivityEventSearchFriend.apply {
            adapter = pagerAdapter
            setPageTransformer(MarginPageTransformer(20))
            setPageTransformer(ZoomOutPageTransformer())
            offscreenPageLimit = 5
        }
    }
}