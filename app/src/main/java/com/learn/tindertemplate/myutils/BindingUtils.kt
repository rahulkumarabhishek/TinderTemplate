package com.learn.tindertemplate.myutils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class BindingUtils {
    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: CircleImageView, url: String?) {
            if (!url.isNullOrEmpty()){
                Glide.with(view.context).load(url)
                    .into(view)
            }
        }
    }
}