package com.example.tvshowlister

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tvshowlister.Fragments.CallBackInterface
import com.example.tvshowlister.Fragments.FirstFragment
import com.example.tvshowlister.Fragments.SecondFragment

class MainActivity : AppCompatActivity(), CallBackInterface {

    private val firstFragment = FirstFragment().apply {
        callBackInterface = this@MainActivity
    }
    private val secondFragment = SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutFragment, firstFragment)
            commit()
        }
    }

    override fun onCallBack(tvShowId: Int) {
        val bundle = Bundle()
        bundle.putInt("tvShowId", tvShowId)

        secondFragment.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutFragment, secondFragment)
            //Log.d("TAG", "onCallBack: main class check")
            addToBackStack("second page")
            commit()
        }
    }
}