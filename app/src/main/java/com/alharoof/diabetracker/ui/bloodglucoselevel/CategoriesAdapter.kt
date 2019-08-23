package com.alharoof.diabetracker.ui.bloodglucoselevel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.bloodglucoselevel.model.Category
import com.alharoof.diabetracker.ui.bloodglucoselevel.CategoriesAdapter.CategoryViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.category_list_item.ivIcon
import kotlinx.android.synthetic.main.category_list_item.tvTitle

class CategoriesAdapter(private val categogyList: List<Category>) : Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int = categogyList.size

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.bindView(categogyList[position])
    }

    class CategoryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindView(category: Category) {
            tvTitle.text = category.title
            ivIcon.setImageResource(category.icon)
        }
    }
}
