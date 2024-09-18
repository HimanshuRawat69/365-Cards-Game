package com.example.a365cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

class Game_Actvity : AppCompatActivity() {
    var gameId=""
    var playerNo=0
    val imageResources = listOf(
        R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7, R.drawable.c8,
        R.drawable.c9, R.drawable.c10, R.drawable.c11, R.drawable.c12, R.drawable.c13,R.drawable.c14,
        R.drawable.c15, R.drawable.c16, R.drawable.c17, R.drawable.c18, R.drawable.c19, R.drawable.c20, R.drawable.c21,
        R.drawable.c22, R.drawable.c23, R.drawable.c24, R.drawable.c25, R.drawable.c26,R.drawable.c27,
        R.drawable.c28, R.drawable.c29, R.drawable.c30, R.drawable.c31, R.drawable.c32, R.drawable.c33, R.drawable.c34,
        R.drawable.c35, R.drawable.c36, R.drawable.c37, R.drawable.c38, R.drawable.c39,R.drawable.c40,
        R.drawable.c41,R.drawable.c42, R.drawable.c43, R.drawable.c44, R.drawable.c45, R.drawable.c46, R.drawable.c47,
        R.drawable.c48, R.drawable.c49,R.drawable.c50, R.drawable.c51, R.drawable.c52, R.drawable.c53
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        setContentView(R.layout.activity_game_actvity)
        gameId= intent.getStringExtra("GAMEID").toString()
        playerNo=intent.getIntExtra("PlayerNo",0)


        if(playerNo==1)
        {
            val playerFragment1=Player1_Fragment()
            replaceFragment(playerFragment1)
        }
        else if(playerNo==2)
        {
            val playerFragment2=Player2_Fragment()
            replaceFragment(playerFragment2)
        }
        else if(playerNo==3)
        {
            val playerFragment3=Player3_Fragment()
            replaceFragment(playerFragment3)
        }
        else if(playerNo==4)
        {
            val playerFragment4=Player4_Fragment()
            replaceFragment(playerFragment4)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}