package com.example.foodbooking

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.foodbooking.apis.ApiService
import com.example.foodbooking.data.Oders_Current_Cart
import com.example.foodbooking.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
//    fun listOder(list:ArrayList<Oders_Current_Cart>){
//        this.list= list
//        CurrentCartFragment.create(list)
//
//    }
    val list = ArrayList<Oders_Current_Cart>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val name = this.intent.getStringExtra("MyName")
        val tuoi = this.intent.getIntExtra("Tuoi",0)

        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        viewpager.setUserInputEnabled(false)
        viewpager.adapter = CollectionAdapter(this)
        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            if (item.itemId == R.id.mnHome) {

//                linearCheckOut.visibility = View.GONE
//                linearHome.visibility = View.VISIBLE
                viewpager.setCurrentItem(0)
            } else if (item.itemId == R.id.mnSearch) {
//                linearCheckOut.visibility = View.GONE
//                linearHome.visibility = View.VISIBLE
                viewpager.setCurrentItem(1)
             } else if (item.itemId == R.id.mnOrders) {
//                linearCheckOut.visibility = View.VISIBLE
//                linearHome.visibility = View.GONE
                viewpager.setCurrentItem(2)

            } else if (item.itemId == R.id.mnNotification) {
//                linearCheckOut.visibility = View.GONE
//                linearHome.visibility = View.GONE
                viewpager.setCurrentItem(3)
            } else if (item.itemId == R.id.mnProfile) {
//                linearCheckOut.visibility = View.GONE
//                linearHome.visibility = View.GONE
                viewpager.setCurrentItem(4)
            }
            return@setOnNavigationItemSelectedListener true
        }

        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

                super.onPageSelected(position)
                if (position == 0) {
                    bottom_navigation.selectedItemId = R.id.mnHome
                } else if (position == 1) {
                    bottom_navigation.selectedItemId = R.id.mnSearch
                } else if (position == 2) {
                    bottom_navigation.selectedItemId = R.id.mnOrders
                } else if (position == 3) {
                    bottom_navigation.selectedItemId = R.id.mnNotification
                } else if (position == 4) {
                    bottom_navigation.selectedItemId = R.id.mnProfile
                }
            }

        })

    }


}

class CollectionAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 6


    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return HomeFragment()
        } else if (position == 1) {
            return SearchFragment()
        } else if (position == 2) {
            return OrdersFragment()
        } else if (position == 3) {
            return NotificationFragment()
        } else  return ProfileFragment()

    }


}





