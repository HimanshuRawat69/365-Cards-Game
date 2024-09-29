package com.example.a365cards

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Final_Head_Player1and2_Screen : DialogFragment() {
    lateinit var gameReference: DatabaseReference
    var playerNum=0
    var player=""
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_final__head__player1and2__screen, null)

        val inputField: EditText = view.findViewById(R.id.inputField)
        val submitButton: Button = view.findViewById(R.id.submitButton)
        val retrievedValueText: TextView = view.findViewById(R.id.retrievedValueText)

        val myActivity = activity as? Game_Actvity
        val gameid=myActivity?.gameId
        playerNum= myActivity?.playerNo!!
        gameReference = FirebaseDatabase.getInstance().reference.child("games").child(gameid!!)

        submitButton.setOnClickListener {
            val inputText = inputField.text.toString()
            if (inputText.isNotEmpty()) {
                val input = inputText.toInt()

                if (input in 4..13) {
                    storeInputInFirebase(input)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please enter a number between 4 and 13",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else {
                Toast.makeText(requireContext(), "Input field cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }


        builder.setView(view)

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

        player="Player"+playerNum+" Team Head"
        gameReference.child(player).setValue(input).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Failed to store input", Toast.LENGTH_SHORT).show()
            }
        }
    }

}