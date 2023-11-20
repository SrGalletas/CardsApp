package com.example.cardsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ItemsAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemsAdapter()
        binding.itemsList.layoutManager = LinearLayoutManager(this)
        binding.itemsList.adapter = adapter


        val retrofit = Retrofit.Builder()
            .baseUrl("https://deckofcardsapi.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val cardApi = retrofit.create(CardDeckApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val cards = cardApi.getAllCards()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(cards.cards)
                }
            }
        }
    }
}