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
import com.example.foodbooking.data.Plan_food
import com.example.foodbooking.fragment.DeliveryFragment
import com.squareup.picasso.Picasso


class PlanAdapter(val food:List<Plan_food>, val context: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.plant_item,parent,false)
        return PlanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return food.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentPlan_food =  food[position]
        (holder as PlanViewHolder).bindData(currentPlan_food)

    }


}
class PlanViewHolder( val view: View) : RecyclerView.ViewHolder(view){
    val imgPlanFood = view.findViewById<ImageView>(R.id.imgPlan)
    val tvNameFood = view.findViewById<TextView>(R.id.tvNamePlanFood)
    val tvDetailFood = view.findViewById<TextView>(R.id.tvDetailPlanFood)
    val tvCuisines = view.findViewById<TextView>(R.id.tvCuisinesPlan)
    val tvAddRess = view.findViewById<TextView>(R.id.tvAddressPlan)
    val tvCode = view. findViewById<TextView>(R.id.tvDetailPlanFoodCode)

    fun bindData(foods : Plan_food){
        tvNameFood.text = foods.NamePlanFood
        tvDetailFood.text = foods.DetailPlanFood
        tvCuisines.text= foods.CuisicesPlan
        tvAddRess.text = foods.AddressPlan
        Picasso.get().load(foods.ImagePlanFood).into(imgPlanFood)
        tvCode.text = "Code: ${foods.CodePlan}"

        view.setOnClickListener {
            (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.flHome,  DeliveryFragment.create(foods.DeliveryIdPlan.toString(),foods.ImagePlanFood,foods.NamePlanFood)).addToBackStack(null).commit()
        }
    }
}