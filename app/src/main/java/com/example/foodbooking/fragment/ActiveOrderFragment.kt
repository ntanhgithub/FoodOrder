package com.example.foodbooking.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.adapter.OdersActiveAdapter
import com.example.foodbooking.data.Oders_Active


/**
 * A simple [Fragment] subclass.
 */
class ActiveOrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_active_order, container, false)
        val rvActive = view.findViewById<RecyclerView>(R.id.recyclerview_ActiveOrder)
        val Active = ArrayList<Oders_Active>()
//
        Active.add(Oders_Active("The Ultimate Moose Burger",100,100))
        Active.add(Oders_Active("The Ultimate Moose Burger",100,100))
        Active.add(Oders_Active("The Ultimate Moose Burger",100,100))
        Active.add(Oders_Active("The Ultimate Moose Burger",100,100))
        Active.add(Oders_Active("The Ultimate Moose Burger",100,100))
        rvActive.layoutManager = LinearLayoutManager(this.activity)
        rvActive.adapter = OdersActiveAdapter(Active,this.activity)

        return view
    }


}
