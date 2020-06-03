package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val annoyingExWorkManager = (application as AnnoyingExApp).annoyingExWorkManager
        val apiManager = (application as AnnoyingExApp).apiManager
        apiManager.getMsgList("https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json", {response ->
            (application as AnnoyingExApp).listOfMessages = response.messages
        }, {
            Log.i("lol", "fail")
        })

        btnBackgroundTask.setOnClickListener {
            annoyingExWorkManager.startMessageSpamming()
        }

        btnBlock.setOnClickListener {
            annoyingExWorkManager.blockMsg()
        }
    }
}
