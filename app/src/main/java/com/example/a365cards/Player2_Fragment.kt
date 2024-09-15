package com.example.a365cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Player2_Fragment : Fragment() {
    lateinit var gameReference: DatabaseReference
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

        val view=inflater.inflate(R.layout.fragment_player2_, container, false)
        val p3=view.findViewById<ImageView>(R.id.imageView19)
        val p4=view.findViewById<ImageView>(R.id.imageView17)
        val p1=view.findViewById<ImageView>(R.id.imageView18)
        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }


        val myActivity = activity as? Game_Actvity
        val gameid=myActivity?.gameId
        Toast.makeText(requireContext(), "Game lobby created with code: $gameid", Toast.LENGTH_SHORT).show()
        val gameIdView=view.findViewById<TextView>(R.id.textView5)
        gameIdView.text="Game ID: $gameid"
        gameReference = FirebaseDatabase.getInstance().reference.child("games").child(gameid!!)



        gameReference.child("Player3Joined").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val value = dataSnapshot.getValue(Boolean::class.java)
                    if(value==true)
                    {
                        p3.setImageResource(R.drawable.blueboy)
                        showToast("Player 3 Joined")
                    }
                    else{
                        p3.setImageResource(R.drawable.blueboynotactive)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to read value: ${databaseError.toException()}")
            }
        })

        gameReference.child("Player4Joined").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val value = dataSnapshot.getValue(Boolean::class.java)
                    if(value==true)
                    {
                        p4.setImageResource(R.drawable.redboy)
                        showToast("Player 4 Joined")
                    }
                    else{
                        p4.setImageResource(R.drawable.redboynotactive)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to read value: ${databaseError.toException()}")
            }
        })


        gameReference.child("Player1Joined").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val value = dataSnapshot.getValue(Boolean::class.java)
                    if(value==true)
                    {
                        p1.setImageResource(R.drawable.blueboy)
                        showToast("Player 1 Joined")
                    }
                    else{
                        p1.setImageResource(R.drawable.blueboynotactive)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to read value: ${databaseError.toException()}")
            }
        })

        return view
    }
}