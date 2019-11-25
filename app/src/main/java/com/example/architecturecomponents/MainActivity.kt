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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        var jsonPlaceholderApi = retrofit
            .create(JsonPlaceholderApi::class.java)

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
}
