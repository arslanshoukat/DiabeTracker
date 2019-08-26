package com.alharoof.diabetracker.ui.logbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.logbook.model.Category
import com.alharoof.diabetracker.ui.logbook.CategoriesAdapter.CategoryViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.category_list_item.ivIcon
import kotlinx.android.synthetic.main.category_list_item.tvTitle

class CategoriesAdapter(
    private val categories: List<Category>,
    private var onListItemClickListener: OnListItemClickListener<Category>
) : Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.bindView(categories[position])
    }

    inner class CategoryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onListItemClickListener.onItemClicked(categories[adapterPosition])
                }
            }
        }

        fun bindView(category: Category) {
            tvTitle.text = category.title
            ivIcon.setImageResource(category.icon)
        }
    }
}
