package com.example.a365cards

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
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
    var checkAllJoined = 0
    var cardListIndexP1 = listOf<Int>()
    var cardListIndexP2 = listOf<Int>()
    var cardListIndexP3 = listOf<Int>()
    var cardListIndexP4 = listOf<Int>()
    var imageViews = listOf<ImageView>()
    private var selectedCard: ImageView? = null
    var cardIndex = -1
    var cardIndex1 = -1
    var cardIndex2 = -1
    var cardIndex3 = -1
    var cardIndex4 = -1
    var dropCardViewP1: ImageView? = null
    var dropCardViewP2: ImageView? = null
    var dropCardViewP3: ImageView? = null
    var dropCardViewP4: ImageView? = null
    var myActivity: Game_Actvity? = null
    var p2: ImageView? = null
    var p3: ImageView? = null
    var p4: ImageView? = null
    var turnofPlayer = 0
    var endofTurn = 0
    var firstCardTurn = -1
    var max = -1;
    var redTeamCurrentHead = 0
    var blueTeamCurrentHead = 0
    var redTeamScoreView: TextView? = null
    var blueTeamScoreView: TextView? = null
    var playerTeam1Head = 0
    var playerTeam2Head = 0

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

        val view = inflater.inflate(R.layout.fragment_player1_, container, false)

        p2 = view.findViewById<ImageView>(R.id.imageView19)
        p3 = view.findViewById<ImageView>(R.id.imageView17)
        p4 = view.findViewById<ImageView>(R.id.imageView18)
        imageViews = listOf(
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
        val playView = view.findViewById<ImageView>(R.id.imageView20)
        blueTeamScoreView = view.findViewById<TextView>(R.id.textView6)
        redTeamScoreView = view.findViewById<TextView>(R.id.textView7)
        val dropBtn = view.findViewById<Button>(R.id.btn_3d)
        dropCardViewP1 = view.findViewById<ImageView>(R.id.imageView25)
        dropCardViewP2 = view.findViewById<ImageView>(R.id.imageView23)
        dropCardViewP3 = view.findViewById<ImageView>(R.id.imageView24)
        dropCardViewP4 = view.findViewById<ImageView>(R.id.imageView22)


        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }



        myActivity = activity as? Game_Actvity
        val gameid = myActivity?.gameId
        showToast("Game lobby created with code: $gameid")
        val gameIdView = view.findViewById<TextView>(R.id.textView5)
        gameIdView.text = "Game ID: $gameid"
        gameReference = FirebaseDatabase.getInstance().reference.child("games").child(gameid!!)


        gameReference.child("Player2Joined").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val value = dataSnapshot.getValue(Boolean::class.java)
                    if (value == true) {
                        checkAllJoined += 1
                        if (checkAllJoined == 3) {
                            var cardListofList = randomCardNumber()

                            gameReference.child("Player1Cards").setValue(cardListofList.get(0))
                            gameReference.child("Player2Cards").setValue(cardListofList.get(1))
                            gameReference.child("Player3Cards").setValue(cardListofList.get(2))
                            gameReference.child("Player4Cards").setValue(cardListofList.get(3))
                            showToast("Click on Play Button!")
                            playView.visibility = View.VISIBLE
                        }
                        p2?.setImageResource(R.drawable.redboy)
                        showToast("Player 2 Joined")
                    } else {
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
                    if (value == true) {
                        checkAllJoined += 1
                        if (checkAllJoined == 3) {
                            var cardListofList = randomCardNumber()

                            gameReference.child("Player1Cards").setValue(cardListofList.get(0))
                            gameReference.child("Player2Cards").setValue(cardListofList.get(1))
                            gameReference.child("Player3Cards").setValue(cardListofList.get(2))
                            gameReference.child("Player4Cards").setValue(cardListofList.get(3))
                            showToast("Click on Play Button!")
                            playView.visibility = View.VISIBLE
                        }
                        p3?.setImageResource(R.drawable.blueboy)
                        showToast("Player 3 Joined")
                    } else {
                        p3?.setImageResource(R.drawable.blueboynotactive)
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
                    if (value == true) {
                        checkAllJoined += 1
                        if (checkAllJoined == 3) {
                            var cardListofList = randomCardNumber()

                            gameReference.child("Player1Cards").setValue(cardListofList.get(0))
                            gameReference.child("Player2Cards").setValue(cardListofList.get(1))
                            gameReference.child("Player3Cards").setValue(cardListofList.get(2))
                            gameReference.child("Player4Cards").setValue(cardListofList.get(3))
                            showToast("Click on Play Button!")
                            playView.visibility = View.VISIBLE

                        }
                        p4?.setImageResource(R.drawable.redboy)
                        showToast("Player 4 Joined")
                    } else {
                        p4?.setImageResource(R.drawable.redboynotactive)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to read value: ${databaseError.toException()}")
            }
        })


        playView.setOnClickListener {

            playView.visibility = View.GONE


            gameReference.child("Player1Cards").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    cardListIndexP1 =
                        dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                    //var j=0;
                    for (i in imageViews.indices) {
                        imageViews[i].setImageResource(myActivity?.imageResources!![cardListIndexP1[i]])
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("Firebase", "Failed to read value.", databaseError.toException())
                }
            })
            Final_Head_Player1and2_Screen().show(childFragmentManager, "inputDialog")
            Leader_Head_Screen().show(childFragmentManager, "inputDialog")

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
            gameReference.child("Player4Cards").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    cardListIndexP4 =
                        dataSnapshot.children.mapNotNull { it.getValue(Int::class.java) }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            turnofPlayer = 1
            endofTurn = 4


        }

        gameReference.child("Player1 Team Head").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                if (value != null) {
                    playerTeam1Head = value
                    blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$value"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        gameReference.child("Player2 Team Head").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                if (value != null) {
                    playerTeam2Head = value
                    redTeamScoreView?.text = "Red: $redTeamCurrentHead/$value"
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
            if (turnofPlayer == 1) {
                if(firstCardTurn==1) {
                    onDropBtnClick()
                    turnofPlayer++
                    glowAvatar(p2!!)
                }
                else{
                    onDropBtnClick()
                    turnofPlayer++
                    glowAvatar(p2!!)
                }
            } else if (turnofPlayer == 0) {
                Toast.makeText(requireContext(), "Game Not Start Yet!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Not your turn!", Toast.LENGTH_SHORT).show()
            }
            if (turnofPlayer == endofTurn) {
                findHeadWinner()
            }
        }

        gameReference.child("Player2 Card Index")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(Int::class.java)
                    if (value != -1) {
                        cardIndex2 = value!!
                        dropCardViewP2?.setImageResource(myActivity?.imageResources!![cardListIndexP2[value!!]])
                    }
                    if (turnofPlayer == endofTurn) {
                        findHeadWinner()
                    }
                    else if (turnofPlayer != 0) {
                        glowAvatar(p3!!)
                        turnofPlayer++
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        gameReference.child("Player3 Card Index")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(Int::class.java)
                    if (value != -1) {
                        cardIndex3 = value!!
                        dropCardViewP3?.setImageResource(myActivity?.imageResources!![cardListIndexP3[value!!]])
                    }
                    if (turnofPlayer == endofTurn) {
                        findHeadWinner()
                    }
                    else if (turnofPlayer != 0) {
                        glowAvatar(p4!!)
                        turnofPlayer++
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        gameReference.child("Player4 Card Index")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(Int::class.java)
                    if (value != -1) {
                        cardIndex4 = value!!
                        dropCardViewP4?.setImageResource(myActivity?.imageResources!![cardListIndexP4[value!!]])
                    }
                    if (turnofPlayer == endofTurn) {
                        findHeadWinner()
                    }
                    else if (turnofPlayer != 0) {
                        resetGlow(p4!!)
                        turnofPlayer = 1
                    }


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        return view
    }

    private fun randomCardNumber(): List<List<Int>> {
        val numbers = (0..51).toList().shuffled()
        val group1 = numbers.subList(0, 13).sorted()
        val group2 = numbers.subList(13, 26).sorted()
        val group3 = numbers.subList(26, 39).sorted()
        val group4 = numbers.subList(39, 52).sorted()


        return listOf(group1, group2, group3, group4)
    }

    private fun onCardClick(card: ImageView) {
        if (selectedCard == card) {
            return
        }

        selectedCard?.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(200)?.start()
        selectedCard?.setBackgroundResource(0)

        selectedCard = card
        card.setBackgroundResource(R.drawable.player_selected)

        card.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).start()
    }

    private fun onDropBtnClick() {
        if (selectedCard != null) {

            selectedCard!!.setImageDrawable(null)
            selectedCard!!.setBackgroundResource(0)
            if(endofTurn==4)
            {
                firstCardTurn=cardListIndexP1[cardIndex]
            }
            gameReference.child("Player1 Card Index").setValue(cardIndex)

            dropCardViewP1?.setImageResource(myActivity?.imageResources!![cardListIndexP1[cardIndex]])
            cardIndex1 = cardIndex
            selectedCard = null
        } else {
            Toast.makeText(requireContext(), "Please select a card first", Toast.LENGTH_SHORT)
                .show()
        }
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


    private fun glowAvatar(avatar: ImageView) {
        // Reset the glow effect from all avatars
        resetGlow(p2!!)
        resetGlow(p3!!)
        resetGlow(p4!!)

        // Set glow effect as a background
        avatar.setBackgroundResource(R.drawable.glow_effect)
    }

    // Function to remove glow effect
    private fun resetGlow(avatar: ImageView) {
        avatar.setBackgroundResource(0) // Removes background
    }


    private fun findHeadWinner() {
        if (cardIndex1 >= 0 && cardIndex2 >= 0 && cardIndex3 >= 0 && cardIndex4 >= 0) {
            max = cardListIndexP1[cardIndex1]
            if (firstCardTurn in 0..12) {
                if (cardListIndexP2[cardIndex2] in 0..12) {
                    max = maxOf(cardListIndexP2[cardIndex2], cardListIndexP1[cardIndex1])
                }
                if (cardListIndexP3[cardIndex3] in 0..12) {
                    max = maxOf(cardListIndexP3[cardIndex3], cardListIndexP1[cardIndex1], max)
                }
                if (cardListIndexP4[cardIndex4] in 0..12) {
                    max = maxOf(cardListIndexP4[cardIndex4], cardListIndexP1[cardIndex1], max)
                }
                if (max == cardListIndexP1[cardIndex1]) {
                    turnofPlayer = 1
                    firstCardTurn = 1
                    endofTurn = 4
                    blueTeamCurrentHead++
                    blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                    Toast.makeText(requireContext(), "Player1 won 1 head!", Toast.LENGTH_SHORT)
                        .show()
                } else if (max == cardListIndexP2[cardIndex2]) {
                    turnofPlayer = 2
                    firstCardTurn = 2
                    endofTurn = 1
                    redTeamCurrentHead++
                    redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                    Toast.makeText(requireContext(), "Player2 won 1 head!", Toast.LENGTH_SHORT)
                        .show()
                } else if (max == cardListIndexP3[cardIndex3]) {
                    turnofPlayer = 3
                    firstCardTurn = 3
                    endofTurn = 2
                    blueTeamCurrentHead++
                    blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                    Toast.makeText(requireContext(), "Player3 won 1 head!", Toast.LENGTH_SHORT)
                        .show()
                } else if (max == cardListIndexP4[cardIndex4]) {
                    turnofPlayer = 4
                    firstCardTurn = 4
                    endofTurn = 3
                    redTeamCurrentHead++
                    redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                    Toast.makeText(requireContext(), "Player4 won 1 head!", Toast.LENGTH_SHORT)
                        .show()
                }
                dropCardViewP1?.setImageResource(R.drawable.drophere)
                dropCardViewP2?.setImageResource(R.drawable.drophere)
                dropCardViewP3?.setImageResource(R.drawable.drophere)
                dropCardViewP4?.setImageResource(R.drawable.drophere)
            } else if (firstCardTurn in 13..25) {
                if (cardListIndexP2[cardIndex2] in 0..12 || cardListIndexP3[cardIndex3] in 0..12 || cardListIndexP4[cardIndex4] in 0..12) {
                    if (cardListIndexP2[cardIndex2] in 0..12) {
                        max = cardListIndexP2[cardIndex2]
                    }
                    if (cardListIndexP3[cardIndex3] in 0..12) {
                        max = maxOf(cardListIndexP3[cardIndex3], max)
                    }
                    if (cardListIndexP4[cardIndex4] in 0..12) {
                        max = maxOf(cardListIndexP4[cardIndex4], max)
                    }
                    if (max == cardListIndexP2[cardIndex2]) {
                        turnofPlayer = 2
                        firstCardTurn = 2
                        endofTurn = 1
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player2 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP3[cardIndex3]) {
                        turnofPlayer = 3
                        firstCardTurn = 3
                        endofTurn = 2
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player3 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP4[cardIndex4]) {
                        turnofPlayer = 4
                        firstCardTurn = 4
                        endofTurn = 3
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player4 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    dropCardViewP1?.setImageResource(R.drawable.drophere)
                    dropCardViewP2?.setImageResource(R.drawable.drophere)
                    dropCardViewP3?.setImageResource(R.drawable.drophere)
                    dropCardViewP4?.setImageResource(R.drawable.drophere)

                } else {
                    if (cardListIndexP2[cardIndex2] in 13..25) {
                        max = maxOf(cardListIndexP2[cardIndex2], cardListIndexP1[cardIndex1])
                    }
                    if (cardListIndexP3[cardIndex3] in 13..25) {
                        max = maxOf(cardListIndexP3[cardIndex3], cardListIndexP1[cardIndex1], max)
                    }
                    if (cardListIndexP4[cardIndex4] in 13..25) {
                        max = maxOf(cardListIndexP4[cardIndex4], cardListIndexP1[cardIndex1], max)
                    }
                    if (max == cardListIndexP1[cardIndex1]) {
                        turnofPlayer = 1
                        firstCardTurn = 1
                        endofTurn = 4
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player1 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP2[cardIndex2]) {
                        turnofPlayer = 2
                        firstCardTurn = 2
                        endofTurn = 1
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player2 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP3[cardIndex3]) {
                        turnofPlayer = 3
                        firstCardTurn = 3
                        endofTurn = 2
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player3 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP4[cardIndex4]) {
                        turnofPlayer = 4
                        firstCardTurn = 4
                        endofTurn = 3
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player4 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    dropCardViewP1?.setImageResource(R.drawable.drophere)
                    dropCardViewP2?.setImageResource(R.drawable.drophere)
                    dropCardViewP3?.setImageResource(R.drawable.drophere)
                    dropCardViewP4?.setImageResource(R.drawable.drophere)
                }
            } else if (firstCardTurn >= 26 && firstCardTurn <= 38) {
                if (cardListIndexP2[cardIndex2] in 0..12 || cardListIndexP3[cardIndex3] in 0..12 || cardListIndexP4[cardIndex4] in 0..12) {
                    if (cardListIndexP2[cardIndex2] in 0..12) {
                        max = cardListIndexP2[cardIndex2]
                    }
                    if (cardListIndexP3[cardIndex3] in 0..12) {
                        max = maxOf(cardListIndexP3[cardIndex3], max)
                    }
                    if (cardListIndexP4[cardIndex4] in 0..12) {
                        max = maxOf(cardListIndexP4[cardIndex4], max)
                    }
                    if (max == cardListIndexP2[cardIndex2]) {
                        turnofPlayer = 2
                        firstCardTurn = 2
                        endofTurn = 1
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player2 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP3[cardIndex3]) {
                        turnofPlayer = 3
                        firstCardTurn = 3
                        endofTurn = 2
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player3 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP4[cardIndex4]) {
                        turnofPlayer = 4
                        firstCardTurn = 4
                        endofTurn = 3
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player4 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    dropCardViewP1?.setImageResource(R.drawable.drophere)
                    dropCardViewP2?.setImageResource(R.drawable.drophere)
                    dropCardViewP3?.setImageResource(R.drawable.drophere)
                    dropCardViewP4?.setImageResource(R.drawable.drophere)

                } else {
                    if (cardListIndexP2[cardIndex2] in 26..38) {
                        max = maxOf(cardListIndexP2[cardIndex2], cardListIndexP1[cardIndex1])
                    }
                    if (cardListIndexP3[cardIndex3] in 26..38) {
                        max = maxOf(cardListIndexP3[cardIndex3], cardListIndexP1[cardIndex1], max)
                    }
                    if (cardListIndexP4[cardIndex4] in 26..38) {
                        max = maxOf(cardListIndexP4[cardIndex4], cardListIndexP1[cardIndex1], max)
                    }
                    if (max == cardListIndexP1[cardIndex1]) {
                        turnofPlayer = 1
                        firstCardTurn = 1
                        endofTurn = 4
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player1 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP2[cardIndex2]) {
                        turnofPlayer = 2
                        firstCardTurn = 2
                        endofTurn = 1
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player2 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP3[cardIndex3]) {
                        turnofPlayer = 3
                        firstCardTurn = 3
                        endofTurn = 2
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player3 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP4[cardIndex4]) {
                        turnofPlayer = 4
                        firstCardTurn = 4
                        endofTurn = 3
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player4 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    dropCardViewP1?.setImageResource(R.drawable.drophere)
                    dropCardViewP2?.setImageResource(R.drawable.drophere)
                    dropCardViewP3?.setImageResource(R.drawable.drophere)
                    dropCardViewP4?.setImageResource(R.drawable.drophere)
                }
            } else if (firstCardTurn in 39..51) {
                if (cardListIndexP2[cardIndex2] in 0..12 || cardListIndexP3[cardIndex3] in 0..12 || cardListIndexP4[cardIndex4] in 0..12) {
                    if (cardListIndexP2[cardIndex2] in 0..12) {
                        max = cardListIndexP2[cardIndex2]
                    }
                    if (cardListIndexP3[cardIndex3] in 0..12) {
                        max = maxOf(cardListIndexP3[cardIndex3], max)
                    }
                    if (cardListIndexP4[cardIndex4] in 0..12) {
                        max = maxOf(cardListIndexP4[cardIndex4], max)
                    }
                    if (max == cardListIndexP2[cardIndex2]) {
                        turnofPlayer = 2
                        firstCardTurn = 2
                        endofTurn = 1
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player2 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP3[cardIndex3]) {
                        turnofPlayer = 3
                        firstCardTurn = 3
                        endofTurn = 2
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player3 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP4[cardIndex4]) {
                        turnofPlayer = 4
                        firstCardTurn = 4
                        endofTurn = 3
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player4 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    dropCardViewP1?.setImageResource(R.drawable.drophere)
                    dropCardViewP2?.setImageResource(R.drawable.drophere)
                    dropCardViewP3?.setImageResource(R.drawable.drophere)
                    dropCardViewP4?.setImageResource(R.drawable.drophere)

                } else {
                    if (cardListIndexP2[cardIndex2] in 39..51) {
                        max = maxOf(cardListIndexP2[cardIndex2], cardListIndexP1[cardIndex1])
                    }
                    if (cardListIndexP3[cardIndex3] in 39..51) {
                        max = maxOf(cardListIndexP3[cardIndex3], cardListIndexP1[cardIndex1], max)
                    }
                    if (cardListIndexP4[cardIndex4] in 39..51) {
                        max = maxOf(cardListIndexP4[cardIndex4], cardListIndexP1[cardIndex1], max)
                    }
                    if (max == cardListIndexP1[cardIndex1]) {
                        turnofPlayer = 1
                        firstCardTurn = 1
                        endofTurn = 4
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player1 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP2[cardIndex2]) {
                        turnofPlayer = 2
                        firstCardTurn = 2
                        endofTurn = 1
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player2 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP3[cardIndex3]) {
                        turnofPlayer = 3
                        firstCardTurn = 3
                        endofTurn = 2
                        blueTeamCurrentHead++
                        blueTeamScoreView?.text = "Blue: $blueTeamCurrentHead/$playerTeam1Head"
                        Toast.makeText(requireContext(), "Player3 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (max == cardListIndexP4[cardIndex4]) {
                        turnofPlayer = 4
                        firstCardTurn = 4
                        endofTurn = 3
                        redTeamCurrentHead++
                        redTeamScoreView?.text = "Red: $redTeamCurrentHead/$playerTeam2Head"
                        Toast.makeText(requireContext(), "Player4 won 1 head!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    dropCardViewP1?.setImageResource(R.drawable.drophere)
                    dropCardViewP2?.setImageResource(R.drawable.drophere)
                    dropCardViewP3?.setImageResource(R.drawable.drophere)
                    dropCardViewP4?.setImageResource(R.drawable.drophere)
                }
            }
        }
        if(turnofPlayer==2)
        {
            glowAvatar(p2!!)
        }
        else if(turnofPlayer==3)
        {
            glowAvatar(p3!!)
        }
        else if(turnofPlayer==4)
        {
            glowAvatar(p4!!)
        }
        gameReference.child("BlueTeamCurrentHead").setValue(blueTeamCurrentHead)
        gameReference.child("RedTeamCurrentHead").setValue(redTeamCurrentHead)
        gameReference.child("PlayerTurn").setValue(turnofPlayer)
    }
}

