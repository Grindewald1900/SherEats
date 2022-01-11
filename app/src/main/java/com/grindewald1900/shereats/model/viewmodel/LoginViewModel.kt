package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grindewald1900.shereats.model.entity.FirebaseUser
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil


/**
 * Created by Yee on 2021-12-12.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class LoginViewModel: BaseViewModel() {
    private var state: MutableLiveData<Int> = MutableLiveData()
    // Firebase version
    private var firebaseUser: MutableLiveData<FirebaseUser> = MutableLiveData()

    fun getUser(): LiveData<FirebaseUser>{
        return firebaseUser
    }

    /**
     * Retrieve data from server
     */
    fun setFirebaseUser(name: String, password: String){
        var user: FirebaseUser?
        RealtimeUtil.userReference.child(name).get().addOnSuccessListener {
            if(null != it.getValue(FirebaseUser::class.java)){
                user = it.getValue(FirebaseUser::class.java)
                if(null != user){
                    if (user!!.userPassword == password){
                        firebaseUser.postValue(user)
                        LoginStatusUtil.setUser(user!!.userId!!, user!!.userName!!, user!!.userPassword!!, user!!.userMail!!)
                        setState(ConstantUtil.ACTIVITY_STATE_LOGIN_SUCCESS)
                    }else{
                        setState(ConstantUtil.ACTIVITY_STATE_LOGIN_FAIL)
                    }
                }
            }else{
                setState(ConstantUtil.ACTIVITY_STATE_NO_USER)
            }
        }.addOnFailureListener {
            setState(ConstantUtil.ACTIVITY_STATE_LOGIN_FAIL)
            it.stackTrace
        }
    }



    fun getState(): LiveData<Int>{
        return state
    }

    fun setState(state: Int){
        this.state.postValue(state)
    }
}