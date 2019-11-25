package com.example.architecturecomponents

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceholderApi {
    @GET("posts")
    fun getPosts(
        @Query("userId") userId: Int = 2,
        @Query("_sort") sort: String = "id",
        @Query("_order") order: String = "desc"
    ): Call<List<Post>>

    @GET("posts")
    fun getPosts(@QueryMap params: Map<String, String>): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Call<List<Comment>>

    @GET
    fun getCommentsUrl(@Url url:String): Call<List<Comment>>

}