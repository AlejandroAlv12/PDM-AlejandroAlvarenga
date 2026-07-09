package com.pdm0126.labo6.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithOptions(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val options: List<Option>
)
