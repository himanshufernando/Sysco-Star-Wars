package com.example.syscostarwars.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.syscostarwars.R
import com.example.syscostarwars.databinding.PagingAdapterFooterViewItemBinding

class PagingLoadStateAdapter (private val retry: () -> Unit) : LoadStateAdapter<PagingLoadStateAdapter.ReposLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ReposLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingLoadStateAdapter.ReposLoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.paging_adapter_footer_view_item, parent, false)
        val binding = PagingAdapterFooterViewItemBinding.bind(view)
        return ReposLoadStateViewHolder(binding, retry)

    }

    inner class ReposLoadStateViewHolder(private val binding: PagingAdapterFooterViewItemBinding, retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.also {
                it.setOnClickListener { retry.invoke() }
            }
        }

        fun bind(loadState: LoadState) {
            binding.gif1.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading

        }


    }

}