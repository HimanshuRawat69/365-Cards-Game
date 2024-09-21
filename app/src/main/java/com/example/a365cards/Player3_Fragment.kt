package com.example.a365cards

import android.os.Bundle
import android.util.Log
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

class Player3_Fragment : Fragment() {
    lateinit var gameReference: DatabaseReference
    var checkAllJoined=0
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

        val view = inflater.inflate(R.layout.fragment_player3_, container, false)
        val p4=view.findViewById<ImageView>(R.id.imageView19)
        val p1=view.findViewById<ImageView>(R.id.imageView17)
        val p2=view.findViewById<ImageView>(R.id.imageView18)
        val playView=view.findViewById<ImageView>(R.id.imageView20)
        val imageViews= listOf(
            view.findViewById<ImageView>(R.id.imageView7),
            view.findViewById<ImageView>(R.id.imageView8),
            view.findViewById<ImageView>(R.id.imageView6),
            view.findViewById<ImageView>(R.id.imageView4),
            view.findViewById<ImageView>(R.id.imageView5),
            view.findViewById<ImageView>(R.id.imageView9),
            view.findViewById<ImageView>(R.id.imageView10),
            view.findViewById<ImageView>(R.id.imageView11),
            view.findViewById<ImageView>(R.id.imageView12),
            view.findViewById<ImageView>(R.id.imageView13),
            view.findViewById<ImageView>(R.id.imageView14),
            view.findViewById<ImageView>(R.id.imageView15),
            view.findViewById<ImageView>(R.id.imageView16)
        )
        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        val myActivity = activity as? Game_Actvity
        val gameid = myActivity?.gameId
        Toast.makeText(
            requireContext(),
            "Game lobby created with code: $gameid",
            Toast.LENGTH_SHORT
        ).show()
        val gameIdView = view.findViewById<TextView>(R.id.textView5)
        gameIdView.text = "Game ID: $gameid"
        gameReference = FirebaseDatabase.getInstance().reference.child("games").child(gameid!!)


        gameReference.child("Player4Joined").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val value = dataSnapshot.getValue(Boolean::class.java)
                    if(value==true)
                    {
                        checkAllJoined+=1
                        if(checkAllJoined==3)
                        {
                            showToast("Click on Play Button!")
                            playView.visibility=View.VISIBLE
                        }
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
                        checkAllJoined+=1
                        if(checkAllJoined==3)
                        {
                            showToast("Click on Play Button!")
                            playView.visibility=View.VISIBLE
                        }
                        p1.setImageResource(R.drawable.blueboy)
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


        gameReference.child("Player2Joined").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val value = dataSnapshot.getValue(Boolean::class.java)
                    if(value==true)
                    {
                        checkAllJoined+=1
                        if(checkAllJoined==3)
                        {
                            showToast("Click on Play Button!")
                            playView.visibility=View.VISIBLE
                        }
                        p2.setImageResource(R.drawable.redboy)
                        showToast("Player 2 Joined")
                    }
                    else{
                        p2.setImageResource(R.drawable.redboynotactive)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to read value: ${databaseError.toException()}")
            }
        })

        playView.setOnClickListener{
            playView.visibility=View.GONE
            gameReference.child("Player3Cards").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val cardList = dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                    //var j=0;
                    for (i in imageViews.indices) {
                        imageViews[i].setImageResource(myActivity?.imageResources!![cardList[i]])
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("Firebase", "Failed to read value.", databaseError.toException())
                }
            })
            Leader_Head_Screen().show(childFragmentManager, "inputDialog")

        }

        return view
    }

}