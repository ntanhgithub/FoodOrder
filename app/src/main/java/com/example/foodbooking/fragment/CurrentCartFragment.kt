package com.example.foodbooking.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.HomeActivity
import com.example.foodbooking.MainActivity
import com.example.foodbooking.R
import com.example.foodbooking.adapter.OdersCurrentAdapter
import com.example.foodbooking.data.ItemOders
import com.example.foodbooking.data.Oders_Current_Cart
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class CurrentCartFragment : Fragment() {
    companion object {
        fun create(list: ArrayList<Oders_Current_Cart>): CurrentCartFragment {
            val fragment = CurrentCartFragment()
            fragment.listCurren = list
            return fragment
        }
    }

    var listCurren = ArrayList<Oders_Current_Cart>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_cart, container, false)
      val rvCurrentCart = view.findViewById<RecyclerView>(R.id.recyclerview_CurrentCart)

        val item1 = ArrayList<Oders_Current_Cart>()

        for(item in (this.activity as HomeActivity).list){
            item1.add(item)
        }

        rvCurrentCart.layoutManager = LinearLayoutManager(this.activity)
        rvCurrentCart.adapter = OdersCurrentAdapter(item1,this.activity)
        return view
    }


}
