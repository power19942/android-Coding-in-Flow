package com.example.architecturecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                var originalRequest = it.request()
                var newRequest = originalRequest.newBuilder()
                    .header("headerName","value").build()
                it.proceed(newRequest)
            }
            .addInterceptor(interceptor)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


        jsonPlaceholderApi = retrofit
            .create(JsonPlaceholderApi::class.java)

        //deletePost()
        //patchPost()
        putPost()
        //createPostFormUrlEncoded()
        //createPost()
        /*getPost(
            mapOf("id" to "1","_sort" to "id")
        )*/
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

    fun getPost(params: Map<String, String>) {
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

    fun createPost() {
        var post = Post(32, "new", "new")
        var call = jsonPlaceholderApi.createPost(post)
        call.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                var post = response.body()!!
                var posts = response.body()!!

                var content: String = ""
                content += "ID: ${post.id} \n"
                content += "User ID: ${post.userId} \n"
                content += "Title: ${post.title} \n"
                content += "Body: ${post.text} \n"

                txt.text = content

            }

        })
    }

    fun putPost() {
        var post = Post(12, null, "new text")
        var call = jsonPlaceholderApi.putPost(5, post)
        call.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                var post = response.body()!!
                var posts = response.body()!!

                var content: String = ""
                content += "ID: ${post.id} \n"
                content += "User ID: ${post.userId} \n"
                content += "Title: ${post.title} \n"
                content += "Body: ${post.text} \n"

                txt.text = content
            }

        })
    }

    fun patchPost() {
        var post = Post(15, null, "new text")
        var call = jsonPlaceholderApi.patchPost(6, post)
        call.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                var post = response.body()!!
                var posts = response.body()!!

                var content: String = ""
                content += "ID: ${post.id} \n"
                content += "User ID: ${post.userId} \n"
                content += "Title: ${post.title} \n"
                content += "Body: ${post.text} \n"

                txt.text = content
            }

        })
    }

    fun deletePost() {
        var call = jsonPlaceholderApi.deletePost(6)
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@MainActivity, "Done", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun createPostFormUrlEncoded() {
        var post = Post(32, "new", "new")
        var call = jsonPlaceholderApi.createPost(
            12, "data", "text"
        )
        call.enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                var post = response.body()!!
                var posts = response.body()!!

                var content: String = ""
                content += "ID: ${post.id} \n"
                content += "User ID: ${post.userId} \n"
                content += "Title: ${post.title} \n"
                content += "Body: ${post.text} \n"

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
