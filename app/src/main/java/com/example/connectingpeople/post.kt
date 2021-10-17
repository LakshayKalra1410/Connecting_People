package com.example.connectingpeople

data class Post(val text:String="",
                val imageUrl: String?= null,
                val user: User = User(),
                val time: Long= 0L,
                val likesList:MutableList<String> = mutableListOf())
