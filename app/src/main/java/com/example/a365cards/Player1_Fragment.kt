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
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Player1_Fragment : Fragment() {
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

        val view=inflater.inflate(R.layout.fragment_player1_, container, false)
//        val imageResources = listOf(
//            R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7, R.drawable.c8,
//            R.drawable.c9, R.drawable.c10, R.drawable.c11, R.drawable.c12, R.drawable.c13,R.drawable.c14,
//            R.drawable.c15, R.drawable.c16, R.drawable.c17, R.drawable.c18, R.drawable.c19, R.drawable.c20, R.drawable.c21,
//            R.drawable.c22, R.drawable.c23, R.drawable.c24, R.drawable.c25, R.drawable.c26,R.drawable.c27,
//            R.drawable.c28, R.drawable.c29, R.drawable.c30, R.drawable.c31, R.drawable.c32, R.drawable.c33, R.drawable.c34,
//            R.drawable.c35, R.drawable.c36, R.drawable.c37, R.drawable.c38, R.drawable.c39,R.drawable.c40,
//            R.drawable.c41,R.drawable.c42, R.drawable.c43, R.drawable.c44, R.drawable.c45, R.drawable.c46, R.drawable.c47,
//            R.drawable.c48, R.drawable.c49,R.drawable.c50, R.drawable.c51, R.drawable.c52, R.drawable.c53
//        )
        val p2=view.findViewById<ImageView>(R.id.imageView19)
        val p3=view.findViewById<ImageView>(R.id.imageView17)
        val p4=view.findViewById<ImageView>(R.id.imageView18)
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
        val playView=view.findViewById<ImageView>(R.id.imageView20)

        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }


        val myActivity = activity as? Game_Actvity
        val gameid=myActivity?.gameId
        showToast("Game lobby created with code: $gameid")
        val gameIdView=view.findViewById<TextView>(R.id.textView5)
        gameIdView.text="Game ID: $gameid"
        gameReference = FirebaseDatabase.getInstance().reference.child("games").child(gameid!!)


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

        gameReference.child("Player3Joined").addValueEventListener(object : ValueEventListener {
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


        playView.setOnClickListener{
            var cardListofList=randomCardNumber()

            gameReference.child("Player1Cards").setValue(cardListofList.get(0))
            gameReference.child("Player2Cards").setValue(cardListofList.get(1))
            gameReference.child("Player3Cards").setValue(cardListofList.get(2))
            gameReference.child("Player4Cards").setValue(cardListofList.get(3))
            playView.visibility=View.GONE


            gameReference.child("Player1Cards").addValueEventListener(object : ValueEventListener {
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
    private fun randomCardNumber():List<List<Int>>
    {
        val numbers = (0..51).toList().shuffled()
        val group1 = numbers.subList(0, 13)
        val group2 = numbers.subList(13, 26)
        val group3 = numbers.subList(26, 39)
        val group4 = numbers.subList(39, 52)

        return listOf(group1, group2, group3, group4)
    }


}