package com.example.hwarchdemo.ui

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.hwarchdemo.R
import com.example.hwarchdemo.getposts.ui.PostBanned
import com.example.hwarchdemo.getposts.ui.PostRegular
import com.example.hwarchdemo.getposts.ui.PostUiModel

@BindingAdapter("app:postTitleFromModel")
fun postTitleFromModel(view: TextView, post: PostUiModel) {
    val context = view.context

    when (post) {
        is PostRegular -> {
            if (post.hasWarning) {
                view.text = context.resources.getString(R.string.warned_user_title, post.userId)
            } else {
                view.text = context.resources.getString(R.string.regular_user_title, post.userId)
            }
        }
        is PostBanned -> {
            view.text = context.resources.getString(R.string.banned_user_title, post.userId)
        }
    }
}

@BindingAdapter("app:postBackGroundColorFromModel")
fun postBackGroundColorFromModel(view: CardView, warning: Boolean) {
    val context = view.context

    if (warning) {
        view.setCardBackgroundColor(context.getColorStateList(R.color.post_warned_color))
    } else {
        view.setCardBackgroundColor(context.getColorStateList(R.color.white))
    }

}

@BindingAdapter("app:postBodyFromModel")
fun postBodyFromModel(view: TextView, body: String) {
    val context = view.context
    view.text = context.resources.getString(R.string.post_body, body)
}

@BindingAdapter("app:messageTitleFromModel")
fun messageTitleFromModel(view: TextView, title: String){
    val context = view.context
    view.text = context.resources.getString(R.string.post_title, title)
}