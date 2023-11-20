package com.example.cardsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardsapp.databinding.ListItemBinding


class ItemsAdapter() : ListAdapter<Card, ItemsAdapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)
        fun bind(card: Card) = with(binding) {
            cardValue.text = card.value
            cardSuit.text = card.suit

            fun getSuitImage(suitName: String?): Int {

                when (suitName) {
                    "CLUBS" -> return R.drawable.clubs
                    "DIAMONDS" -> return R.drawable.diamonds
                    "HEARTS" -> return R.drawable.hearts
                    "SPADES" -> return R.drawable.spades
                }
                return 0
            }

            getSuitImage(card.suit)

            Glide.with(cardImage).load(card.image).into(cardImage)
            Glide.with(cardSuitImage).load(getSuitImage(card.suit)).into(cardSuitImage)
        }
    }

    class Comparator : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
