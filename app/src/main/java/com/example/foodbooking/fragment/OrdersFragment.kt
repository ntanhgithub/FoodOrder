package com.example.foodbooking.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.foodbooking.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * A simple [Fragment] subclass.
 */
class OrdersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        val viewPager = view.findViewById<ViewPager2>(R.id.pager)
        val tablayout = view.findViewById<BottomNavigationView>(R.id.tablayout)
        viewPager.adapter = CollectionAdapter1(this)

        tablayout.setOnNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId == R.id.mnCurrentCart) {
                viewPager.setCurrentItem(0)
            } else if (item.itemId == R.id.mnActiveOrders) {
                viewPager.setCurrentItem(1)
            } else if (item.itemId == R.id.mnPreOrders) {
                viewPager.setCurrentItem(2)
            }
            return@setOnNavigationItemSelectedListener true
        }
        viewPager.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    tablayout.selectedItemId = R.id.mnCurrentCart
                } else if (position == 1) {
                    tablayout.selectedItemId = R.id.mnActiveOrders
                } else if (position == 2) {
                    tablayout.selectedItemId = R.id.mnPreOrders
                }
            }
        })

        return view
    }

}
class CollectionAdapter1(fragmentActivity: OrdersFragment) :
    FragmentStateAdapter(fragmentActivity)  {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return CurrentCartFragment()
        } else if (position == 1) {
            return ActiveOrderFragment()

        } else return PreOrdersFragment()
    }


}