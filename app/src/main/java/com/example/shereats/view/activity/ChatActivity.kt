package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shereats.R
import com.example.shereats.databinding.ActivityChatBinding
import com.example.shereats.model.entity.FirebaseChat
import com.example.shereats.model.viewmodel.ChatViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.view.adapter.ChatAdapter

class ChatActivity : FragmentActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: ChatViewModel
    private var chat: FirebaseChat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        initView()
    }

    private fun initView(){
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        if (intent.extras != null){
            chat = intent.getSerializableExtra(ConstantUtil.ENTITY_CHAT) as FirebaseChat
            viewModel.setFriend(chat!!.friend!!)
            viewModel.setChat(chat!!)
        }
        if(chat != null){
            binding.tvActivityChatTitle.text = chat!!.friend!!.userName
        }

        binding.btnActivityChatSend.setOnClickListener {
            viewModel.updateChat(binding.etActivityChat.text.toString(), ConstantUtil.TYPE_TEXT)
            binding.etActivityChat.text.clear()
        }

        viewModel.getChat().observe(this){
            setRecyclerView(it)
        }
        setEditText()
    }


    private fun setEditText(){
        val editText = binding.etActivityChat
        editText.doOnTextChanged { _, _, _, _ ->
            if(editText.text.toString().isNotEmpty()){
                setSendButton(true)
            }else{
                setSendButton(false)
            }
        }
    }

    private fun setSendButton(isSend: Boolean){
        if(isSend){
            binding.ivActivityChatAdd.visibility = View.GONE
            binding.btnActivityChatSend.visibility = View.VISIBLE
        }else{
            binding.ivActivityChatAdd.visibility = View.VISIBLE
            binding.btnActivityChatSend.visibility = View.GONE
        }
    }

    private fun setRecyclerView(chat: FirebaseChat){
        val msg = chat.messageList
        if (msg != null && msg.size > 0){
            binding.rvActivityChat.apply {
                layoutManager =  LinearLayoutManager(context)
                adapter = ChatAdapter(msg)
            }
        }
    }
}