package com.annaginagili.waterapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.annaginagili.waterapp.R
import com.annaginagili.waterapp.api.Article
import com.squareup.picasso.Picasso

class NewsAdapter(var articles: MutableList<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val article_title = itemView.findViewById<TextView>(R.id.article_title)
        val article_description = itemView.findViewById<TextView>(R.id.article_description)
        val article_image = itemView.findViewById<ImageView>(R.id.newsImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.article_title.text = article.title
        holder.article_description.text = article.description
        Picasso.get().load(article.urlToImage).into(holder.article_image)
    }

    fun addData(newArticles: List<Article>) {
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}