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
    fun getCommentsUrl(@Url url: String): Call<List<Comment>>

    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String
    ): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@FieldMap params: Map<String, Any>): Call<Post>


    @PUT("posts/{id}")
    fun putPost(@Path("id")id:Int,@Body post:Post): Call<Post>

    @PATCH("posts/{id}")
    fun patchPost(@Path("id")id:Int,@Body post:Post): Call<Post>


    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int): Call<Void>
}