package com.swaz.notebook.model

data class Note(
    val id : String = "",
    val title : String = "",
    val content : String = "",
    val timestamp : Long =System.currentTimeMillis()
)