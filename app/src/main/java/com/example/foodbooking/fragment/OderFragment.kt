package com.example.foodbooking.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.adapter.OderAdapter
import com.example.foodbooking.data.Oder
import io.sulek.ssml.SSMLLinearLayoutManager

/**
 * A simple [Fragment] subclass.
 */
class OderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view =  inflater.inflate(R.layout.fragment_oder, container, false)
        val rvOder = view.findViewById<RecyclerView>(R.id.rycyViewOder)
        val oder = ArrayList<Oder>()

        oder.add(Oder("Order #1","03 Oct 2018"))
        oder.add(Oder("Order #2","03 Oct 2018"))
        oder.add(Oder("Order #3","03 Oct 2018"))
        oder.add(Oder("Order #4","03 Oct 2018"))
        oder.add(Oder("Order #5","03 Oct 2018"))
        oder.add(Oder("Order #6","03 Oct 2018"))

        rvOder.layoutManager = SSMLLinearLayoutManager(this.activity as Context)
        rvOder.adapter = OderAdapter(oder,this.activity)

        return view
    }


}
