package com.example.a365cards

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Player4_Fragment : Fragment() {
    lateinit var gameReference: DatabaseReference
    var checkAllJoined=0
    var cardListIndexP1= listOf<Int>()
    var cardListIndexP2= listOf<Int>()
    var cardListIndexP3= listOf<Int>()
    var cardListIndexP4= listOf<Int>()
    var imageViews= listOf<ImageView>()
    private var selectedCard: ImageView? = null
    var cardIndex=-1;
    var dropCardViewP1: ImageView? = null
    var dropCardViewP2: ImageView? = null
    var dropCardViewP3: ImageView? = null
    var dropCardViewP4: ImageView? = null
    var myActivity: Game_Actvity? =null
    var p1: ImageView? = null
    var p2: ImageView? = null
    var p3: ImageView? = null
    var turnofPlayer=0

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

        val view = inflater.inflate(R.layout.fragment_player4_, container, false)
        p1=view.findViewById<ImageView>(R.id.imageView19)
        p2=view.findViewById<ImageView>(R.id.imageView17)
        p3=view.findViewById<ImageView>(R.id.imageView18)
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
        val blueTeamScoreView=view.findViewById<TextView>(R.id.textView6)
        val redTeamScoreView=view.findViewById<TextView>(R.id.textView7)
        val dropBtn=view.findViewById<Button>(R.id.btn_3d)
        dropCardViewP4=view.findViewById<ImageView>(R.id.imageView25)
        dropCardViewP1=view.findViewById<ImageView>(R.id.imageView23)
        dropCardViewP2=view.findViewById<ImageView>(R.id.imageView24)
        dropCardViewP3=view.findViewById<ImageView>(R.id.imageView22)

        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        myActivity = activity as? Game_Actvity
        val gameid = myActivity?.gameId
        Toast.makeText(
            requireContext(),
            "Game lobby created with code: $gameid",
            Toast.LENGTH_SHORT
        ).show()
        val gameIdView = view.findViewById<TextView>(R.id.textView5)
        gameIdView.text = "Game ID: $gameid"
        gameReference = FirebaseDatabase.getInstance().reference.child("games").child(gameid!!)


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
                        p1?.setImageResource(R.drawable.blueboy)
                    }
                    else{
                        p1?.setImageResource(R.drawable.blueboynotactive)
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
                        p2?.setImageResource(R.drawable.redboy)
                        showToast("Player 2 Joined")
                    }
                    else{
                        p2?.setImageResource(R.drawable.redboynotactive)
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
                        p3?.setImageResource(R.drawable.blueboy)
                        showToast("Player 3 Joined")
                    }
                    else{
                        p3?.setImageResource(R.drawable.blueboynotactive)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to read value: ${databaseError.toException()}")
            }
        })

        playView.setOnClickListener{
            playView.visibility=View.GONE
            gameReference.child("Player4Cards").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    cardListIndexP4 = dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                    //var j=0;
                    for (i in imageViews.indices) {
                        imageViews[i].setImageResource(myActivity?.imageResources!![cardListIndexP4[i]])
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("Firebase", "Failed to read value.", databaseError.toException())
                }
            })
            Leader_Head_Screen().show(childFragmentManager, "inputDialog")

            gameReference.child("Player1Cards").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    cardListIndexP1 =
                        dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            gameReference.child("Player2Cards").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    cardListIndexP2 =
                        dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            gameReference.child("Player3Cards").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    cardListIndexP3 =
                        dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            turnofPlayer=1
            glowAvatar(p1!!)

        }

        gameReference.child("Player1 Team Head").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                if(value!=null)
                {
                    blueTeamScoreView.text="Blue: 0/$value"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        gameReference.child("Player2 Team Head").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                if(value!=null)
                {
                    redTeamScoreView.text="Blue: 0/$value"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        imageViews.forEach { card ->
            card.setOnClickListener {
                onCardClick(card)
                cardIndex = imageViews.indexOf(card)
            }
        }

        dropBtn.setOnClickListener {
            if(turnofPlayer==4) {
                onDropBtnClick()
                turnofPlayer=1
                glowAvatar(p1!!)
            }
            else if(turnofPlayer==0)
            {
                Toast.makeText(requireContext(),"Game Not Start Yet!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(),"Not your turn!",Toast.LENGTH_SHORT).show()
            }
        }

        gameReference.child("Player1 Card Index").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                if(value!=-1)
                {
                    dropCardViewP1?.setImageResource(myActivity?.imageResources!![cardListIndexP1[value!!]] )
                }
                if(turnofPlayer!=0) {
                    glowAvatar(p2!!)
                    turnofPlayer++
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        gameReference.child("Player2 Card Index").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                if(value!=-1)
                {
                    dropCardViewP2?.setImageResource(myActivity?.imageResources!![cardListIndexP2[value!!]] )
                }
                if(turnofPlayer!=0) {
                    glowAvatar(p3!!)
                    turnofPlayer++
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        gameReference.child("Player3 Card Index").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                if(value!=-1)
                {
                    dropCardViewP3?.setImageResource(myActivity?.imageResources!![cardListIndexP3[value!!]] )
                }
                if(turnofPlayer!=0) {
                    resetGlow(p3!!)
                    turnofPlayer++
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return view
    }

    private fun onCardClick(card: ImageView) {
        // If the clicked card is already selected, just return (no change)
        if (selectedCard == card) {
            return
        }

        // Reset the scale of the previously selected card, if any
        selectedCard?.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(200)?.start()
        selectedCard?.setBackgroundResource(0)  // Remove background from previously selected card

        // Set the new selected card and apply the zoom effect
        selectedCard = card
        card.setBackgroundResource(R.drawable.player_selected)  // Apply the selected background

        // Apply the zoom effect (scale up the card)
        card.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start()  // Zoom in to 120% of its size
    }

    private fun onDropBtnClick()
    {
        if (selectedCard != null) {

            selectedCard!!.setImageDrawable(null)
            selectedCard!!.setBackgroundResource(0)

            gameReference.child("Player4 Card Index").setValue(cardIndex)

            dropCardViewP4?.setImageResource(myActivity?.imageResources!![cardListIndexP4[cardIndex]] )

            cardIndex=-1
            selectedCard = null
        } else {
            Toast.makeText(requireContext(), "Please select a card first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun glowAvatar(avatar: ImageView) {
        resetGlow(p1!!)
        resetGlow(p2!!)
        resetGlow(p3!!)

        avatar.setBackgroundResource(R.drawable.glow_effect)
    }

    private fun resetGlow(avatar: ImageView) {
        avatar.setBackgroundResource(0)
    }
}