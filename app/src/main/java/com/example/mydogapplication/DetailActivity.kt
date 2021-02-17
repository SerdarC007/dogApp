package com.example.mydogapplication

import android.content.Intent
import android.icu.number.NumberFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.dogs_rv_layout.view.*
import model.DogApi

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
            //intent.getStringArrayExtra("message")


       var detailImage = findViewById<ImageView>(R.id.detail_image)

        var intent = intent.getStringExtra("message")
        var url = ("https://dog.ceo/api/breeds/image/random/50")
        if (intent != null) {
            url = intent
        }
        //Picasso.get().load("https://dog.ceo/api/breeds/image/random/50").fit().into(detailImage)
        Picasso.get().load(url).fit().centerInside().into(detailImage)
    }
}