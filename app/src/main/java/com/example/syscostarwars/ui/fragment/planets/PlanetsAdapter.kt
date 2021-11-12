package com.example.syscostarwars.ui.fragment.planets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.syscostarwars.data.Result
import com.example.syscostarwars.databinding.ListviewPlanetsBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetsAdapter @Inject constructor(): PagingDataAdapter<Result, RecyclerView.ViewHolder>(PLANET_COMPARATOR) {

    companion object {
        private val PLANET_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }

    lateinit var mClickListener: ClickListener


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = getItem(position)
        (holder as ItemViewHolder).bind(item!!)
    }

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(selectedPlanet: Result, aView: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ListviewPlanetsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            mClickListener

        )

    }

    class ItemViewHolder(
        private val binding: ListviewPlanetsBinding,
        var mClickListener: ClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.result?.let { selectedTrack ->
                    mClickListener.onClick(selectedTrack, it, absoluteAdapterPosition)
                }
            }
        }

        fun bind(rec: Result) {
            binding.apply {
                result = rec
                executePendingBindings()
            }
        }
    }


}