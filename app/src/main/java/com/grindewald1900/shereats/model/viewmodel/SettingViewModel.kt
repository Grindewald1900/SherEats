package com.grindewald1900.shereats.model.viewmodel

import androidx.lifecycle.MutableLiveData
import com.grindewald1900.shereats.model.entity.FirebaseBadge
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.utils.LoginStatusUtil
import com.grindewald1900.shereats.utils.firebase.RealtimeUtil

class SettingViewModel: BaseViewModel() {
    private var state: MutableLiveData<Int> = MutableLiveData()
    private var badgeSequence: Long = 0

    fun uploadBadge(badgeType: Long){
        badgeSequence ++
        val resourceId = badgeType.toInt() - 1
        val userId = LoginStatusUtil.getUserId()
        val badgeContent = ConstantUtil.BADGE_CONTENT[resourceId]
        val badgeRarity = ConstantUtil.BADGE_RARITY[resourceId]
        val badgeDate = ConstantUtil.getCurrentDate()
        val badge = FirebaseBadge(userId, badgeType, badgeType, badgeSequence, badgeContent, badgeDate, badgeRarity)
        RealtimeUtil.badgeReference.child(userId).child(badgeSequence.toString()).setValue(badge)

    }

    fun getBadgeSequence(){
        val userId = LoginStatusUtil.getUserId()
        RealtimeUtil.badgeReference.child(userId).get().addOnSuccessListener {
            if (it.childrenCount > 0){
                badgeSequence = it.childrenCount
            }
        }
    }


}