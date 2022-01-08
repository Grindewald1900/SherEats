package com.example.shereats.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.shereats.R
import com.example.shereats.databinding.ActivityChatBinding
import com.example.shereats.model.entity.FirebaseChat
import com.example.shereats.model.entity.FirebaseMessage
import com.example.shereats.model.viewmodel.ChatViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.example.shereats.view.adapter.ChatAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatActivity : BaseActivityNoBar() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: ChatViewModel
    private var isInit = false
    private var isShowEmoji = false
    private var msg: MutableList<FirebaseMessage> = mutableListOf()
    private var bitmapUser: Bitmap? = null
    private var bitmapFriend: Bitmap? = null

    private var chat: FirebaseChat? = null
    private var imageUri: Uri? = null
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            imageUri = it.data?.data
            if(null != imageUri){
                viewModel.upLoadImage(imageUri!!, this)
            }
        }
    }

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

        binding.ivActivityChatAdd.setOnClickListener {
            selectImage()
        }
        binding.btnActivityChatSend.setOnClickListener {
            viewModel.updateChat(binding.etActivityChat.text.toString(), ConstantUtil.TYPE_TEXT)
            binding.etActivityChat.text.clear()
        }
        binding.ivActivityChatBack.setOnClickListener {
            onBackPressed()
        }
        binding.ivActivityChatEmoji.setOnClickListener {
            isShowEmoji = !isShowEmoji
            if(isShowEmoji){
                binding.emojiActivityChat.visibility = View.VISIBLE
            }else{
                binding.emojiActivityChat.visibility = View.GONE
            }
        }
        viewModel.getChat().observe(this){
            setRecyclerView(it)
        }
        setEditText()
        setUserImage()
        setEmojiListener()
    }


    private fun setUserImage(){
        val pathReferenceUser = StorageUtil.reference.child("user/${LoginStatusUtil.getUserName()}.jpg")
        val pathReferenceFriend = StorageUtil.reference.child("user/${chat!!.friend!!.userName}.jpg")
        pathReferenceUser.downloadUrl.addOnSuccessListener {
            lifecycleScope.launch {
                getUserImage(it, 1)
            }
        }
        pathReferenceFriend.downloadUrl.addOnSuccessListener {
            lifecycleScope.launch {
                getUserImage(it,2)
            }
        }


    }

    private suspend fun getUserImage(uri: Uri, type: Int){
        val context = this@ChatActivity
        withContext(Dispatchers.IO){
            Glide.with(context)
                .asBitmap()
                .load(uri)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        when(type){
                            1 -> {bitmapUser = resource}
                            2 -> {bitmapFriend = resource}
                        }
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
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

    private fun setEmojiListener(){
        val grid = binding.emojiActivityChat
        val size = grid.childCount
        for (i in 0 until size){
            val container = grid.getChildAt(i)
            container.setOnClickListener {
                viewModel.updateChat("emoji_$i", ConstantUtil.TYPE_EMOJI)
            }
        }
    }

    private fun setRecyclerView(chat: FirebaseChat){
        if (chat.messageList != null && chat.messageList.size > 0){
            binding.rvActivityChat.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ChatAdapter(chat.messageList, bitmapUser, bitmapFriend)
                scrollToPosition(chat.messageList.size - 1)
            }
        }
    }

    /**
     * Choose image from photo gallery
     */
    private fun selectImage(){
        var intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        intent = Intent.createChooser(intent, "Select Image")

        resultLauncher.launch(intent)
    }
}