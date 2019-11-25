package com.example.architecturecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var jsonPlaceholderApi: JsonPlaceholderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        jsonPlaceholderApi = retrofit
            .create(JsonPlaceholderApi::class.java)


        getPost(
            mapOf("id" to "1","_sort" to "id")
        )
        //getPost()
        //getComments()

    }

    fun getPost() {
        var call = jsonPlaceholderApi.getPosts()

        call.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                txt.text = t.message
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    txt.text = "error on response ${response.code()}"
                    return
                }

                var posts = response.body()!!

                var content: String = ""
                for (post in posts) {
                    content += "ID: ${post.id} \n"
                    content += "User ID: ${post.userId} \n"
                    content += "Title: ${post.title} \n"
                    content += "Body: ${post.text} \n"
                }

                txt.text = content
            }

        })
    }

    fun getPost(params:Map<String,String>) {
        var call = jsonPlaceholderApi.getPosts(params)

        call.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                txt.text = t.message
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    txt.text = "error on response ${response.code()}"
                    return
                }

                var posts = response.body()!!

                var content: String = ""
                for (post in posts) {
                    content += "ID: ${post.id} \n"
                    content += "User ID: ${post.userId} \n"
                    content += "Title: ${post.title} \n"
                    content += "Body: ${post.text} \n"
                }

                txt.text = content
            }

        })
    }

    fun getComments() {
        var call = jsonPlaceholderApi.getComments(10)

        call.enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                txt.text = t.message
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (!response.isSuccessful) {
                    txt.text = "error on response ${response.code()}"
                    return
                }

                var comments = response.body()!!

                var content: String = ""
                for (post in comments) {
                    content += "ID: ${post.id} \n"
                    content += "Post ID: ${post.postId} \n"
                    content += "Name: ${post.name} \n"
                    content += "Body: ${post.text} \n"
                }

                txt.text = content
            }

        })
    }

    fun getCommentswithUrl() {
        var call = jsonPlaceholderApi.getCommentsUrl("posts/10/comments")

        call.enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                txt.text = t.message
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (!response.isSuccessful) {
                    txt.text = "error on response ${response.code()}"
                    return
                }

                var comments = response.body()!!

                var content: String = ""
                for (post in comments) {
                    content += "ID: ${post.id} \n"
                    content += "Post ID: ${post.postId} \n"
                    content += "Name: ${post.name} \n"
                    content += "Body: ${post.text} \n"
                }

                txt.text = content
            }

        })
    }
}
