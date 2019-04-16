package com.tekprosft.navapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.tekprosft.navapp.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.OnListFragmentInteractionListener {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                findNavController(R.id.nav_host).navigate(R.id.main_dest)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                findNavController(R.id.nav_host).navigate(R.id.list_dest)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                findNavController(R.id.nav_host).navigate(R.id.params_dest) // without an action
                findNavController(R.id.nav_host).navigate(R.id.action_global_params) // with action (Global)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
//        val args = Bundle()
//        args.putString("param1","Selected")
//        args.putString("param2",item.toString())
//        findNavController(R.id.nav_host).navigate(R.id.action_list_to_params, args) // using action from list to params fragment with bundle

        //with safargs
        val action = ListFragmentDirections.actionListToParams()
        action.param1 = "Selected (Safe Arguments) "
        action.param2 = item.toString()
        findNavController(R.id.nav_host).navigate(action)
    }
}
