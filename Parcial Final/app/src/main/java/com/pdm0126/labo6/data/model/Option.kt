package com.pdm0126.labo6.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "options")
data class Option(
    @PrimaryKey val id: Int,
    val questionId: Int,
    val value: String,
    val votes: Int
)
