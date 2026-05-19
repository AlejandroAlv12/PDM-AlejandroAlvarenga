package com.pdm0126.labo4.model

import java.util.Date

data class Card(
    val pos: Int,
    val title: String,
    val description: String,
    val endDate: Date,
    val checked: Boolean
)
