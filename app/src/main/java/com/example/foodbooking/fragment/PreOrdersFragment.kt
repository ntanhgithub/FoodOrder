package com.example.foodbooking.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.adapter.PreOdersAdapter
import com.example.foodbooking.data.PreOrders

/**
 * A simple [Fragment] subclass.
 */
class PreOrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pre_orders, container, false)
        val rvPre = view.findViewById<RecyclerView>(R.id.recyclerview_PreOders)
        val pre = ArrayList<PreOrders>()

        pre.add(PreOrders("#123456789","Tue, 30 Oct, 2018 | 15:00","0681 Eleanora Village Suite 427","S\$1220.00"))
        pre.add(PreOrders("#123456789","Tue, 30 Oct, 2018 | 15:00","0681 Eleanora Village Suite 427","S\$1220.00"))
        pre.add(PreOrders("#123456789","Tue, 30 Oct, 2018 | 15:00","0681 Eleanora Village Suite 427","S\$1220.00"))
        pre.add(PreOrders("#123456789","Tue, 30 Oct, 2018 | 15:00","0681 Eleanora Village Suite 427","S\$1220.00"))

        rvPre.layoutManager  = LinearLayoutManager(this.activity)
        rvPre.adapter = PreOdersAdapter(pre,this.activity)
        return  view
    }


}
