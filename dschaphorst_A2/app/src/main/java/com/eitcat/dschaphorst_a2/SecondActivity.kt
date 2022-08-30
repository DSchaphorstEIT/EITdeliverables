package com.eitcat.dschaphorst_a2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eitcat.dschaphorst_a2.databinding.ActivitySecondBinding

private const val TAG = "SecondActivity"
const val REPLY_KEY = "com.eitcat.dschaphorst_a2.extra.reply"

class SecondActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textMessage.text = intent?.getStringExtra(MESSAGE_KEY) ?: "[No Message]"
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onResume() {
        super.onResume()
        binding.buttonSecond.setOnClickListener{
            Log.d(TAG, "Reply Button Clicked")
            Intent(baseContext, MainActivity::class.java).apply{
                putExtra(REPLY_KEY, binding.editTextSecond.text.toString())
                setResult(RESULT_OK, this)
            }
            Log.d(TAG, "End SecondActivity")
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("message_text", binding.textMessage.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.textMessage.text = savedInstanceState.getString("message_text")
    }
}