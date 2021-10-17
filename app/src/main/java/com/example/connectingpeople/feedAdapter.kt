package com.example.connectingpeople

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.github.thunder413.datetimeutils.DateTimeStyle
import com.github.thunder413.datetimeutils.DateTimeUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FeedAdapter(options:FirestoreRecyclerOptions<Post>, val context: Context):
    FirestoreRecyclerAdapter<Post, FeedAdapter.FeedViewHolder>(options) {


    class FeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val postImage:ImageView=itemView.findViewById(R.id.post_image)
        val postText: TextView =itemView.findViewById(R.id.post_text)
        val authorText:TextView=itemView.findViewById(R.id.post_author)
        val timeText:TextView=itemView.findViewById(R.id.post_time)
        val likeIcon:ImageView=itemView.findViewById(R.id.like_icon)
        val likeCount:TextView=itemView.findViewById(R.id.like_count)
        val commentIcon:ImageView=itemView.findViewById(R.id.comment_icon)
        val commentCount:TextView=itemView.findViewById(R.id.comment_count)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int, model: Post) {

        holder.postText.text = model.text
        holder.authorText.text = model.user.name

        val date = DateTimeUtils.formatDate(model.time)
        val dateFormatted = DateTimeUtils.formatWithStyle(date, DateTimeStyle.LONG)

        holder.timeText.text = dateFormatted

        Glide.with(context)
            .load(model.imageUrl)