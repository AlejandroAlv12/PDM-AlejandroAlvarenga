package com.pdmcourse2026.basictemplate.ui.models

data class PlaceModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int,
    val isSelected: Boolean = false
)
