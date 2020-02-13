package com.example.foodbooking.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.data.Categories_food
import com.example.foodbooking.fragment.CategoryFragment
import com.squareup.picasso.Picasso


class CategoriesAdapter(val categories: List<Categories_food>, val context: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCategories = categories[position]
        (holder as CategoriesViewHolder).bindData(currentCategories)
    }


}

class CategoriesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val tvCategoriesName = view.findViewById<TextView>(R.id.tvResNameCategories)
    val imageview = view.findViewById<ImageView>(R.id.imgResCategories)

    fun bindData(categories: Categories_food) {
        view.setOnClickListener {
            (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.flHome,  CategoryFragment.create(categories.Id,categories.CategoriesName)).addToBackStack(null).commit()
        }
        tvCategoriesName.text = categories.CategoriesName
        Picasso.get().load(categories.ImgCategories).into(imageview)
    }



}