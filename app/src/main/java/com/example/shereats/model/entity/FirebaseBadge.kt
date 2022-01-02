package com.example.shereats.model.entity

data class FirebaseBadge(
    val userId: String? = null,
    val badgeId: Long? = null,
    val badgeType: Long? = null,
    val badgeSequence: Long? = null,
    val badgeContent: String? = null,
    val badgeDate: String? = null,
    val badgeRarity: Long? = null
)
