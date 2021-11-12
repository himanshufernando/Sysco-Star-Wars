package com.example.syscostarwars.ui.adapter

import android.R.attr
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.syscostarwars.R
import com.example.syscostarwars.data.Result
import android.R.attr.path
import coil.request.CachePolicy


object CustomBindingAdapter {

    @BindingAdapter("setPlanetsPlaceholders")
    @JvmStatic
    fun setPlanetsPlaceholders(view: AppCompatImageView,url : String) {
        try {
            val strs =url.split("/").toTypedArray()
            var url ="https://picsum.photos/id/"+ strs[strs.size-2] +"/200/300"
            view.load(url) {
                crossfade(true)
                error(R.drawable.ic_launcher_background)
                transformations(CircleCropTransformation())
            }
        } catch (e: Exception) {
            view.setImageResource(R.drawable.ic_launcher_background)
        }
    }


    @BindingAdapter("setPlanetImage")
    @JvmStatic
    fun setPlanetImage(view: AppCompatImageView,url : String) {
        try {
            val strs =url.split("/").toTypedArray()
            var url ="https://picsum.photos/id/"+ strs[strs.size-2] +"/600/800"
            view.load(url) {
                crossfade(true)
                error(R.drawable.ic_launcher_background)
            }
        } catch (e: Exception) {
            view.setImageResource(R.drawable.ic_launcher_background)
        }


    }



}