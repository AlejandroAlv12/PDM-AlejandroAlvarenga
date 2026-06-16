package com.pdmcourse2026.basictemplate.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdmcourse2026.basictemplate.data.model.Option
import com.pdmcourse2026.basictemplate.ui.models.PlaceModel

@Entity(tableName = "options")
data class OptionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int
)

fun OptionEntity.toPlaceModel(): PlaceModel = PlaceModel(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    votes = this.votes
)

fun OptionEntity.toModel(): Option = Option(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl
)

fun Option.toEntity(): OptionEntity = OptionEntity(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    votes = 0 // O el valor que corresponda si Option tuviera votos
)
