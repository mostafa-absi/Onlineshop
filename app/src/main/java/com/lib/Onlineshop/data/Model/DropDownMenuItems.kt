package com.lib.Onlineshop.data.Model

data class DropDownMenuItems(
    val name:String,
    val logo: Int,
    val route: String? = null,
    val isAction: Boolean = false
)
