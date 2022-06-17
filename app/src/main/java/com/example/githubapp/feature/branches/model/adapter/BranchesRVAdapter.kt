package com.example.githubapp.feature.branches.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.githubapp.databinding.BranchesListItemBinding
import com.example.githubapp.feature.branches.model.network.model.BranchesDataResponse
import com.example.githubapp.feature.pulls.model.adapter.AdapterCallback

class BranchesRVAdapter<T>(private val adapterCallback: AdapterCallback) : RecyclerView.Adapter<BranchesRVAdapter<T>.BranchesViewHolder>() {

    private val dataList = mutableListOf<T>()

    fun refreshList(data: List<T>) {
        dataList.clear()
        dataList.addAll(data)
        notifyItemRangeChanged(0, dataList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchesViewHolder {
        val binding = BranchesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BranchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BranchesViewHolder, position: Int) {
        holder.bind(dataList[position], adapterCallback)
    }

    override fun getItemCount(): Int = dataList.size

    inner class BranchesViewHolder(private val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T, adapterCallback: AdapterCallback) {
            data as BranchesDataResponse
            binding as BranchesListItemBinding

            binding.tvBranchName.text = data.name
            binding.tvCommitID.text = data.commit.sha
            binding.root.setOnClickListener {
                adapterCallback.itemClicked(data.commit.url)
            }
        }
    }
}