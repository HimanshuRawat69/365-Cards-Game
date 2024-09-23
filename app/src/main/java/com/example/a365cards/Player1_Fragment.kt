package com.example.a365cards

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
    var cardList= listOf<Int>()
    private var selectedCard: ImageView? = null
    var cardIndex=-1;
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
        val blueTeamScoreView=view.findViewById<TextView>(R.id.textView6)
        val redTeamScoreView=view.findViewById<TextView>(R.id.textView7)


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
                            var cardListofList=randomCardNumber()

                            gameReference.child("Player1Cards").setValue(cardListofList.get(0))
                            gameReference.child("Player2Cards").setValue(cardListofList.get(1))
                            gameReference.child("Player3Cards").setValue(cardListofList.get(2))
                            gameReference.child("Player4Cards").setValue(cardListofList.get(3))
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
                            var cardListofList=randomCardNumber()

                            gameReference.child("Player1Cards").setValue(cardListofList.get(0))
                            gameReference.child("Player2Cards").setValue(cardListofList.get(1))
                            gameReference.child("Player3Cards").setValue(cardListofList.get(2))
                            gameReference.child("Player4Cards").setValue(cardListofList.get(3))
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
                            var cardListofList=randomCardNumber()

                            gameReference.child("Player1Cards").setValue(cardListofList.get(0))
                            gameReference.child("Player2Cards").setValue(cardListofList.get(1))
                            gameReference.child("Player3Cards").setValue(cardListofList.get(2))
                            gameReference.child("Player4Cards").setValue(cardListofList.get(3))
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

            playView.visibility=View.GONE


            gameReference.child("Player1Cards").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        cardList = dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                        //var j=0;
                        for (i in imageViews.indices) {
                            imageViews[i].setImageResource(myActivity?.imageResources!![cardList[i]])
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.w("Firebase", "Failed to read value.", databaseError.toException())
                    }
                })
            Final_Head_Player1and2_Screen().show(childFragmentManager, "inputDialog")
            Leader_Head_Screen().show(childFragmentManager, "inputDialog")

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
                    redTeamScoreView.text="Red: 0/$value"
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

        return view
    }
    private fun randomCardNumber():List<List<Int>>
    {
        val numbers = (0..51).toList().shuffled()
        val group1 = numbers.subList(0, 13).sorted()
        val group2 = numbers.subList(13, 26).sorted()
        val group3 = numbers.subList(26, 39).sorted()
        val group4 = numbers.subList(39, 52).sorted()


        return listOf(group1, group2, group3, group4)
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

//    private fun onCardClick(card: ImageView) {
//        // If the clicked card is already selected, just return (no change)
//        if (selectedCard == card) {
//            return
//        }
//
//        // Remove background from the previously selected card, if any
//        selectedCard?.setBackgroundResource(0)
//
//        // Set the new selected card and update its background
//        selectedCard = card
//        card.setBackgroundResource(R.drawable.player_selected)  // Change to the selected background
//    }


}