package com.abit.taskapp.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import com.abit.taskapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import javax.sql.DataSource


fun ImageView.loadImage(img: String){
    Glide.with(this).load(img).into(this)
}

fun ImageView.loadImageGallery(url: String){
    Glide.with(this).load(url).placeholder(R.drawable.ic_person).into(this)

}