package com.example.architecturecomponents

import com.google.gson.annotations.SerializedName

data class Post(
    var userId: Int,
    var title: String,
    @SerializedName("body")
    var text: String,
    var id: Int? = null
)