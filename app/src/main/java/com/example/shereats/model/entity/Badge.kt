package com.example.shereats.model.entity


/**
 * Created by Yee on 2021-12-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 *
 * @param user_id: the user who hold this badge
 * @param badge_id: a unique id
 * @param badge_type: 1-9, represent the image resource of this badge
 * @param badge_sequence: 1-MAX, represent the order of badges that was obtained
 * @param badge_content: the explanation of this badge
 * @param badge_date: 2021-01-01
 * @param badge_rarity: 1-5, free, common, rare, epic, legendary
 */
data class Badge(
    val user_id: String,
    val badge_id: Int,
    val badge_type: Int,
    val badge_sequence: Int,
    val badge_content: String,
    val badge_date: String,
    val badge_rarity: Int
)
