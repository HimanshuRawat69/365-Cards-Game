package com.example.a365cards

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Leader_Head_Screen : DialogFragment() {
    lateinit var gameReference: DatabaseReference
    var playerNum=0
    var player=""
    private lateinit var closeButton: Button
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_leader__head__screen, null)

        val inputField: EditText = view.findViewById(R.id.inputField)
        val submitButton: Button = view.findViewById(R.id.submitButton)
        val retrievedValueText: TextView = view.findViewById(R.id.retrievedValueText)
        closeButton=view.findViewById<Button>(R.id.closeButton)

        val myActivity = activity as? Game_Actvity
        val gameid=myActivity?.gameId
        playerNum= myActivity?.playerNo!!
        gameReference = FirebaseDatabase.getInstance().reference.child("games").child(gameid!!)

        submitButton.setOnClickListener {
            val inputText = inputField.text.toString()
            if (inputText.isNotEmpty()) {
                val input = inputText.toInt()

                if (input in 1..13) {
                    storeInputInFirebase(input)
                    closeButton.visibility = View.VISIBLE
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please enter a number between 1 and 13",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else {
                Toast.makeText(requireContext(), "Input field cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        retrieveLastInput(retrievedValueText)

        builder.setView(view)
        closeButton.visibility = View.GONE

        closeButton.setOnClickListener {
            dismiss()
        }

        this.isCancelable = false

        return builder.create()
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val params: WindowManager.LayoutParams = window.attributes
            params.gravity = Gravity.TOP
            params.y = 5
            window.attributes = params
            val scale = resources.displayMetrics.density
            val dialogWidth = (300 * scale + 0.5f).toInt()
            val dialogHeight = (250 * scale + 0.5f).toInt()

            window.setLayout(
                dialogWidth,
                dialogHeight
            )
        }
    }

    private fun storeInputInFirebase(input: Int) {
        player="Player"+playerNum+" Head"
        gameReference.child(player).setValue(input).addOnCompleteListener { task ->
            if (task.isSuccessful) {

            } else {
                Toast.makeText(requireContext(), "Failed to store input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun retrieveLastInput(retrievedValueText: TextView) {
        if(playerNum==1)
        {
            gameReference.child("Player3 Head").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(Int::class.java)
                        if (value != null && value>0) {
                            retrievedValueText.text = "Player3 Head: $value"
                            closeButton.visibility = View.VISIBLE
                        }
                        else{
                            retrievedValueText.text = "Player3 Head: Calculating!!"
                        }

                    }
                    else{
                        retrievedValueText.text = "Player3 Head: Calculating!!"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
        else if(playerNum==2)
        {
            gameReference.child("Player4 Head").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(Int::class.java)
                        if (value != null && value>0) {
                            retrievedValueText.text = "Player4 Head: $value"
                            closeButton.visibility = View.VISIBLE
                        }
                        else{
                            retrievedValueText.text = "Player4 Head: Calculating!!"
                        }
                    }
                    else{
                        retrievedValueText.text = "Player4 Head: Calculating!!"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
        else if(playerNum==3)
        {
            gameReference.child("Player1 Head").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(Int::class.java)
                        if (value != null && value>0) {
                            retrievedValueText.text = "Player1 Head: $value"
                            closeButton.visibility = View.VISIBLE
                        }
                        else{
                            retrievedValueText.text = "Player1 Head: Calculating!!"
                        }
                    }
                    else{
                        retrievedValueText.text = "Player1 Head: Calculating!!"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
        else if(playerNum==4)
        {
            gameReference.child("Player2 Head").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(Int::class.java)
                        if (value != null && value>0) {
                            retrievedValueText.text = "Player2 Head: $value"
                            closeButton.visibility = View.VISIBLE
                        }
                        else{
                            retrievedValueText.text = "Player2 Head: Calculating!!"
                        }
                    }
                    else{
                        retrievedValueText.text = "Player2 Head: Calculating!!"
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

    }
    private fun setHeadZero()
    {
        player="Player"+playerNum+" Head"
        gameReference.child(player).setValue(0).addOnCompleteListener { task ->
            if (task.isSuccessful) {

            } else {
                Toast.makeText(requireContext(), "Failed to store input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}