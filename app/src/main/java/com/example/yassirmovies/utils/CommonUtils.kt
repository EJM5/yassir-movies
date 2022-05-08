package com.example.yassirmovies.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.load(imageURL: String?, placeHolderId: Int) {

    var imageRequestBuilder = Glide.with(this).load(imageURL).skipMemoryCache(true).dontAnimate()

    if (imageURL.isNullOrBlank()) {
        imageRequestBuilder = imageRequestBuilder.placeholder(placeHolderId)
    }

    imageRequestBuilder = imageRequestBuilder.listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            imageRequestBuilder = imageRequestBuilder.placeholder(placeHolderId)
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            return false
        }
    })

    imageRequestBuilder.into(this)
}