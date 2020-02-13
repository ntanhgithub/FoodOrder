package com.example.foodbooking.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbooking.R
import com.example.foodbooking.adapter.NeventAdapter
import com.example.foodbooking.data.NewEvents
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * A simple [Fragment] subclass.
 */
class NewEventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_event, container, false)
        val rvNevent = view.findViewById<RecyclerView>(R.id.rvNewEvent)


        val nEvent = ArrayList<NewEvents>()

        nEvent.add(NewEvents("123", "03 Oct 2018"))
        nEvent.add(NewEvents("1234", "03 Oct 2018"))
        nEvent.add(NewEvents("12345", "03 Oct 2018"))
        nEvent.add(NewEvents("123456", "03 Oct 2018"))
        nEvent.add(NewEvents("1236789", "03 Oct 2018"))
        nEvent.add(NewEvents("1234", "03 Oct 2018"))
        nEvent.add(NewEvents("12322", "03 Oct 2018"))
        nEvent.add(NewEvents("12344", "03 Oct 2018"))


        rvNevent.layoutManager = LinearLayoutManager(this.activity)
        rvNevent.adapter = NeventAdapter(nEvent, this.activity)


        return view
    }


}
