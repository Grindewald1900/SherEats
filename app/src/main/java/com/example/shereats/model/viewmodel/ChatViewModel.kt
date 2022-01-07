package com.example.shereats.model.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shereats.R
import com.example.shereats.model.entity.FirebaseChat
import com.example.shereats.model.entity.FirebaseMessage
import com.example.shereats.model.entity.FirebaseUser
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.ImageUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.ToastUtil
import com.example.shereats.utils.firebase.RealtimeUtil
import com.example.shereats.utils.firebase.StorageUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatViewModel: BaseViewModel() {
    private var chat: MutableLiveData<FirebaseChat> = MutableLiveData()
    private var friend: FirebaseUser? = null

    fun getChat(): LiveData<FirebaseChat>{
        return chat
    }

    fun setChat(c: FirebaseChat){
        chat.postValue(c)
    }

    fun updateChat(content: String, type: String){
        val msgList: MutableList<FirebaseMessage> = mutableListOf()
        chat.value!!.messageList?.let { msgList.addAll(it) }
        if(null != friend){
            val time = ConstantUtil.getCurrentTime()
            val msg: FirebaseMessage = when(type){
                ConstantUtil.TYPE_TEXT ->{
                    FirebaseMessage(LoginStatusUtil.getUserName(), friend!!.userName, time, type, content, null, null)
                }
                ConstantUtil.TYPE_IMAGE ->{
                    FirebaseMessage(LoginStatusUtil.getUserName(), friend!!.userName, time, type, null, content, null)
                }
                ConstantUtil.TYPE_VIDEO ->{
                    FirebaseMessage(LoginStatusUtil.getUserName(), friend!!.userName, time, type, null, content, null)
                }
                else -> {
                    FirebaseMessage(LoginStatusUtil.getUserName(), friend!!.userName, time, type, content, null, null)
                }
            }
            msgList.add(msg)
            val chatUser = FirebaseChat(friend,content,time, true, msgList)
            val chatFriend= FirebaseChat(LoginStatusUtil.getUser(), content,time, true, msgList)
            setChat(chatUser)
            RealtimeUtil.chatReference.child(LoginStatusUtil.getUserName()).child(friend!!.userName!!).setValue(chatUser)
            RealtimeUtil.chatReference.child(friend!!.userName!!).child(LoginStatusUtil.getUserName()).setValue(chatFriend)
        }
    }



    fun setFriend(f: FirebaseUser){
        friend = f
        setChatUpdateListener()
        updateNotice(false)
    }

    private fun updateNotice(notice: Boolean){
        RealtimeUtil.chatReference.child(LoginStatusUtil.getUserName()).child("${friend!!.userName!!}/notice").setValue(notice)
    }

    private fun setChatUpdateListener(){
        if(friend == null) return
        RealtimeUtil.chatReference.child(LoginStatusUtil.getUserName()).child(friend!!.userName!!).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val chat = snapshot.getValue(FirebaseChat::class.java)
                if(null != chat){
                    setChat(chat)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    /**
     *Upload image(local file) to Firebase
     */
    fun upLoadImage(imageUri: Uri, context: Context){
        val format = ImageUtil.getImageFormat(imageUri!!, context).split("/").last()
        val time = ConstantUtil.getCurrentTime()
        val name = LoginStatusUtil.getUserName().plus(friend!!.userName)
        // image/GrindewaldYee/192002992
        val childPath = "image/$name/$time"
        val imageReference = StorageUtil.reference.child(childPath)
        val upLoadTask = imageReference.putFile(imageUri!!)
        upLoadTask.addOnFailureListener{
            it.stackTrace
        }.addOnSuccessListener {
            imageReference.downloadUrl.addOnSuccessListener {
                updateChat(it.toString(), ConstantUtil.TYPE_IMAGE)
            }
        }
    }
}