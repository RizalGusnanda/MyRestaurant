package com.dicoding.myrestaurant

import android.content.Intent
import android.media.Rating
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MoveWithDetailFood : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_detail_food)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("My Restaurant")

        val imgFood: ImageView = findViewById(R.id.img_view_detail_food)
        val titleDetailFood: TextView = findViewById(R.id.title_text_view_detail_food)
        val descriptionDetailFood: TextView = findViewById(R.id.description_text_view_detail_food)
        val priceDetailFood: TextView = findViewById(R.id.price_text_view_detail_food)
        val ratingDetailFood: TextView = findViewById(R.id.ratimg_text_view_detail_food)
        val btnShare: Button = findViewById(R.id.btn_share)

        fun buildShareMessage(title: String, description: String, price: String, rating: String): String {
            return "$title\n\n$description\n\n$price\n\n$rating"
        }
        val shareButton = findViewById<Button>(R.id.btn_share)
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = buildShareMessage("Judul: ", "Deskripsi: ", "Price: ", "Rating: ")
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Bagikan dengan"))
        }

        val food = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DETAIL, Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DETAIL)
        }
        if (food != null) {
            imgFood.setImageResource(food.photo)
            titleDetailFood.text = food.name
            descriptionDetailFood.text = food.description
            priceDetailFood.text = food.price
            ratingDetailFood.text = food.rating
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}