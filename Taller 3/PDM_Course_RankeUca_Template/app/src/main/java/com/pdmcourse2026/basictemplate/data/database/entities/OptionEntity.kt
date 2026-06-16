package com.pdmcourse2026.basictemplate.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdmcourse2026.basictemplate.data.model.Option

@Entity(tableName = "options")
data class OptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageUrl: String
)

// Funciones de extensión (Mappers)
fun OptionEntity.toModel(): Option = Option(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl
)

fun Option.toEntity(): OptionEntity = OptionEntity(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl
)
