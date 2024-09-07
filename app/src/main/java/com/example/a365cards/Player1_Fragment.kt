package com.example.a365cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Player1_Fragment : Fragment() {
    var checkPlayerSelectStatus=0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.decorView?.systemUiVisibility =
            (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        val view=inflater.inflate(R.layout.fragment_player1_, container, false)

        val myActivity = activity as? Game_Actvity
        val gameid=myActivity?.gameId
        Toast.makeText(requireContext(), "Game lobby created with code: $gameid", Toast.LENGTH_SHORT).show()
        val gameIdView=view.findViewById<TextView>(R.id.textView5)
        gameIdView.text="Game ID: $gameid"
        return view
    }


}