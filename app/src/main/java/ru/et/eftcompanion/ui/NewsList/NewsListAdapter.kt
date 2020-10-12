package ru.et.eftcompanion.ui.NewsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*
import ru.et.eftcompanion.R
import ru.et.eftcompanion.model.NewsModel
import ru.et.eftcompanion.util.DateTimeUtil

class NewsListAdapter(items: List<NewsModel>, val dateTimeUtil: DateTimeUtil) : RecyclerView.Adapter<NewsListAdapter.NewsHolder>() {

    var items: ArrayList<NewsModel> = ArrayList()
    var onItemClick: ((NewsModel) -> Unit)? = null

    init {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return NewsHolder(inflater.inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val newsModel = items[position]
        holder.itemView.news_title.text = newsModel.title
        holder.itemView.news_desc.text = newsModel.desc
        holder.itemView.news_date.text = dateTimeUtil.parseLongDateToString(newsModel.date)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context
            get() = itemView.context
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }

}