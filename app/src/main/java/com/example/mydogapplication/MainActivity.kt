package com.example.mydogapplication

import adapter.DogAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import model.DogApi
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity(), DogAdapter.onItemClickListener {

    val imageList = ArrayList<DogApi>()
    private lateinit var dogsRV: RecyclerView
    private lateinit var dogsNameTxt: EditText
    private lateinit var searchButton: FloatingActionButton
    private lateinit var detailImage: ImageView
   //private var adapter = DogAdapter(this ,imageList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogsRV = findViewById(R.id.dogsView)
        //dogsNameTxt = findViewById(R.id.dogName)
        // searchButton = findViewById(R.id.searchButton)

        dogsRV.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)



        searchDogs()
        /*           searchButton.setOnClickListener {
                   var name = dogsNameTxt.text.toString()

                   //Create Search Function

                   searchDogs(name)

               }*/

    }

    private fun searchDogs() {
        imageList.clear()
        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://dog.ceo/api/breeds/image/random/50")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val result = JSONObject(response)

                    //get the name from the object
                    val image = result.getJSONArray("message")

                    //Loop through each Dog item
                    for (i in 0 until image.length()) {
                        val list = image.get(i)
                        imageList.add(
                            DogApi(list.toString())
                        )
                    }
                  dogsRV.adapter = DogAdapter(this@MainActivity,imageList,this@MainActivity)

                }

                override fun onError(anError: ANError?) {
                    TODO("Not yetimplemented")
                }

            })

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position is clicked", Toast.LENGTH_SHORT).show()
       var intent = Intent(applicationContext, DetailActivity::class.java)

        var clickedItem = imageList[position]
       intent.putExtra("message", clickedItem.message)
        startActivity(intent)
    }


}