package com.example.a365cards

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var checkPlayerSelectStatus = 0;
    lateinit var gameReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCreate=findViewById<Button>(R.id.buttonCreate)
        val buttonJoin = findViewById<Button>(R.id.buttonJoin)
        val editTextCode = findViewById<EditText>(R.id.editTextCode)
        val p2=findViewById<ImageView>(R.id.imageView)
        val p3=findViewById<ImageView>(R.id.imageView2)
        val p4=findViewById<ImageView>(R.id.imageView3)



        gameReference = FirebaseDatabase.getInstance().reference.child("games")
        fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        fun checkInternetConnection(): Boolean {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetworkInfo = connectivityManager.activeNetworkInfo

            // Check if there's a network connection
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
            // Connected to the internet
            // You can perform your network operations here
        }

        buttonCreate.setOnClickListener {

            if(!checkInternetConnection())
            {
                showToast("No Internet Connection!!")
                return@setOnClickListener
            }

            val randomCode = generateRandomCode()
            createGameLobby(randomCode)
            checkPlayerSelectStatus=1;
            val intent = Intent(this@MainActivity, Game_Actvity::class.java)
            intent.putExtra("GAMEID", randomCode)
            intent.putExtra("PlayerNo", checkPlayerSelectStatus)
            startActivity(intent)
        }

        p2.setOnClickListener{
            checkPlayerSelectStatus=2
            p2.setBackgroundResource(R.drawable.player_selected)
            p3.background = null
            p4.background = null
        }
        p3.setOnClickListener{
            checkPlayerSelectStatus=3
            p3.setBackgroundResource(R.drawable.player_selected)
            p2.background = null
            p4.background = null
        }
        p4.setOnClickListener{
            checkPlayerSelectStatus=4
            p4.setBackgroundResource(R.drawable.player_selected)
            p2.background = null
            p3.background = null
        }

        buttonJoin.setOnClickListener {

            if (!checkInternetConnection()) {
                showToast("No Internet Connection!!")
                return@setOnClickListener
            }

            val code = editTextCode.text.toString()
            joinGameLobby(code)
        }
    }

    private fun generateRandomCode(): String {
        val characters = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz123456789"
        return (1..6)
            .map { characters.random() }
            .joinToString("")
    }

    private fun createGameLobby(randomCode: String) {
        val Reference = gameReference.child(randomCode)
        val game = hashMapOf(
            "Player1Joined" to true,
            "Player2Joined" to false,
            "Player3Joined" to false,
            "Player4Joined" to false,
            "Player1Cards" to listOf<Int>(),
            "Player2Cards" to listOf<Int>(),
            "Player3Cards" to listOf<Int>(),
            "Player4Cards" to listOf<Int>()
        )

        Reference.setValue(game)
            .addOnSuccessListener {
                Toast.makeText(this,"Game lobby created with code: $randomCode",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this,"Error creating game lobby: $e",Toast.LENGTH_SHORT).show()
            }
    }

    fun joinGameLobby(code: String) {
        val gameRef = gameReference.child(code).child("Player1Joined")
        gameRef.get().addOnSuccessListener {
            var value = it.getValue(Boolean::class.java)
            if (value == true) {
                if (checkPlayerSelectStatus == 0)
                {
                    Toast.makeText(this, "Select Player No!!", Toast.LENGTH_SHORT).show()
                } else if (checkPlayerSelectStatus >= 2 && checkPlayerSelectStatus <= 4)
                {
                    var playerNo = "Player" + checkPlayerSelectStatus + "Joined"
                    gameReference.child(code).child(playerNo).get().addOnSuccessListener {
                            var check = it.getValue(Boolean::class.java)
                            if (check == true)
                            {
                                Toast.makeText(
                                    this,
                                    "Player ${checkPlayerSelectStatus} Table Already Occupied!!", Toast.LENGTH_SHORT).show()
                            }
                            else
                            {
                                gameReference.child(code).child(playerNo).setValue(true)
                                val intent = Intent(this@MainActivity, Game_Actvity::class.java)
                                intent.putExtra("GAMEID", code)
                                intent.putExtra("PlayerNo", checkPlayerSelectStatus)
                                startActivity(intent)
                                Toast.makeText(
                                    this,
                                    "You joined Game with $code",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
                else
                {
                    Toast.makeText(
                        this,
                        "No Game with $code Go back Enter Again!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}