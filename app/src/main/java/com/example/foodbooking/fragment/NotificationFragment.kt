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
class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        val viewpager = view.findViewById<ViewPager2>(R.id.viewPager2)
        viewpager.isUserInputEnabled = false
        val navigation = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        viewpager.adapter = DemoAdapter(this)
        navigation.setOnNavigationItemSelectedListener {  item: MenuItem ->
            if(item.itemId == R.id.oder){
                viewpager.setCurrentItem(0)
            }
            else if(item.itemId == R.id.promotions){
                viewpager.setCurrentItem(1)
            }
            else if(item.itemId == R.id.newandevents){
                viewpager.setCurrentItem(2)
            }

            return@setOnNavigationItemSelectedListener true}

        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 0){
                    navigation.selectedItemId = R.id.oder
                }
                else if(position == 1){
                    navigation.selectedItemId = R.id.promotions
                }
                else if(position == 2){
                    navigation.selectedItemId = R.id.newandevents
                }
            }

        })



        return view
    }


}
class DemoAdapter(fragmentActivity: NotificationFragment) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        if(position == 0){
            return OderFragment()
        }
        else if(position == 1){
            return PromotionFragment()
        }
        else{
            return NewEventFragment()
        }

    }
}
