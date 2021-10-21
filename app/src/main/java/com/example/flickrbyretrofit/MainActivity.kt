package com.example.flickrbyretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recycle: RecyclerView
    lateinit var search: EditText
    lateinit var btnsearch: Button
    lateinit var imgv: ImageView

    var images = ArrayList<Photo>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle = findViewById(R.id.rv)
        search = findViewById(R.id.edsearch)
        imgv = findViewById(R.id.imageView)
        btnsearch = findViewById(R.id.button)

        recycle.adapter = RVadapter(this, images)
        recycle.layoutManager = LinearLayoutManager(this)

        btnsearch.setOnClickListener {
            Log.d("RESULT-Wrong", "hi")
            if (search.text.isNotEmpty()) {
                RequestAPI()
            } else {
                Toast.makeText(applicationContext, "Search is empty", Toast.LENGTH_SHORT).show()
            }
        }
        imgv.setOnClickListener { closeImg() }
    }
    fun RequestAPI() {
        val apiInterface = APIClient().GetClient()?.create(APIInterface::class.java)
        val call: Call<photoDetails?>? = apiInterface!!.doGetListResources(search.text.toString())
        call?.enqueue(object : Callback<photoDetails?> {
            override fun onResponse(
                call: Call<photoDetails?>,
                response: Response<photoDetails?>
            ) {
                for (photo in response.body()!!.photos.photo) {
                    images.add(photo)
                }


                recycle.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<photoDetails?>, t: Throwable) {
                call.cancel()
            }

        })
    }

    fun openImg(link: String){
        Glide.with(this).load(link).into(imgv)
        imgv.isVisible = true
        recycle.isVisible = false
        btnsearch.isVisible = false
        search.isVisible = false
    }

    private fun closeImg(){
        imgv.isVisible = false
        recycle.isVisible = true
        btnsearch.isVisible = true
        search.isVisible = true
    }
}