package com.example.githubapp.feature.pulls.model.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.githubapp.databinding.PullsRvItemBinding
import com.example.githubapp.feature.pulls.model.network.models.PullRequestData

class PullsRVAdapter<T>(private val callback: AdapterCallback) : RecyclerView.Adapter<PullsRVAdapter<T>.PullsItemViewHolder>() {

    private val dataSet = mutableListOf<T>()

    fun refreshList(list: List<T>) {
        dataSet.clear()
        dataSet.addAll(list)
        notifyItemRangeChanged(0, dataSet.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsItemViewHolder {
        val binding = PullsRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullsItemViewHolder, position: Int) {
        holder.bind(dataSet[position], callback)
    }

    override fun getItemCount(): Int = dataSet.size

    inner class PullsItemViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: T, callback: AdapterCallback) {
            model as PullRequestData
            binding as PullsRvItemBinding

            // load image
            Glide.with(binding.ivIcon.context)
                .load(model.user.avatar_url)
                .into(binding.ivIcon)

            // setting data
            with(binding) {
                tvTitle.text = model.title
                tvTag.text = model.state
                tvDescription.text = model.body
                if (model.state == "open") {
                    tvTag.setTextColor((Color.parseColor("#00D100")))
                } else {
                    tvTag.setTextColor((Color.parseColor("#FF5C5C")))
                }

                rvItemRoot.setOnClickListener {
                    callback.itemClicked(model.url)
                }
            }
        }
    }
}