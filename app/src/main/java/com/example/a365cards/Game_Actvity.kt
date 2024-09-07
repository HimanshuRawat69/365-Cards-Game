package com.example.a365cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

class Game_Actvity : AppCompatActivity() {
    var gameId=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        setContentView(R.layout.activity_game_actvity)
        gameId= intent.getStringExtra("GAMEID").toString()
        var playerNo=intent.getIntExtra("PlayerNo",0)

        if(playerNo==1)
        {
            val playerFragment1=Player1_Fragment()
            replaceFragment(playerFragment1)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}