package com.grindewald1900.shereats.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.FragmentFriendBinding
import com.grindewald1900.shereats.model.entity.FirebaseChat
import com.grindewald1900.shereats.model.viewmodel.FriendViewModel
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.view.activity.LoginActivity
import com.grindewald1900.shereats.view.adapter.FriendAdapter

class FriendFragment : Fragment() {
    private lateinit var binding: FragmentFriendBinding
    private lateinit var viewModel: FriendViewModel

    companion object {
        fun newInstance() = FriendFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friend, container, false)
        initView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        initView()

    }

    override fun onPause() {
        super.onPause()
    }

    private fun initView(){
        viewModel = ViewModelProvider(this).get(FriendViewModel::class.java)
        if (LoginStatusUtil.isLogin()){
            viewModel.setChats(LoginStatusUtil.getUserName())
            viewModel.getChats().observe(viewLifecycleOwner){
                initRecyclerView(it)
            }
        }else{
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private fun refreshFriendList(){
        if (LoginStatusUtil.isLogin()){
//            viewModel.setChats(LoginStatusUtil.getUserName())
        }
    }

    private fun initRecyclerView(chats: List<FirebaseChat>){
        binding.rvFragmentFriend.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentFriend.itemAnimator = DefaultItemAnimator()
        binding.rvFragmentFriend.adapter = FriendAdapter(chats)
    }

}