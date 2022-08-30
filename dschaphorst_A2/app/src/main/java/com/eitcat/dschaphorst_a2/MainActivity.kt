package com.eitcat.dschaphorst_a2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.eitcat.dschaphorst_a2.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
const val MESSAGE_KEY = "com.eitcat.dschaphorst_a2.extra.message"

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "--------")
        Log.d(TAG, "onCreate")
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
        binding.buttonMain.setOnClickListener {
            Log.d(TAG, "Send Button Clicked")
            Intent(baseContext, SecondActivity::class.java).apply{
                putExtra(MESSAGE_KEY, binding.editTextMain.text.toString())
                //startActivityForResult(this,    TEXT_REQUEST)
                openActivityForResult(this)
            }
        }
    }

    private fun openActivityForResult(targetIntent: Intent){
        startForResult.launch(targetIntent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode  == Activity.RESULT_OK) {
            binding.textReply.visibility = View.VISIBLE
            binding.replyHeader.visibility = View.VISIBLE
            binding.textReply.text = result.data?.getStringExtra(REPLY_KEY) ?: "[No Message]"
        }
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
//            binding.textReply.visibility = View.VISIBLE
//            binding.replyHeader.visibility = View.VISIBLE
//            binding.textReply.text = data?.getStringExtra(REPLY_KEY) ?: "[No Message]"
//        }
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSave")
        if (binding.textReply.visibility == View.VISIBLE){
            outState.putBoolean("reply_visible", true)
            outState.putString("reply_text", binding.textReply.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, String.format("onRestore: " + savedInstanceState.getBoolean("reply_visible") + " | " + savedInstanceState.getString("reply_text")))
        if (savedInstanceState.getBoolean("reply_visible")) {
            binding.replyHeader.visibility = View.VISIBLE
            binding.textReply.visibility = View.VISIBLE
            binding.textReply.text = savedInstanceState.getString("reply_text")
        }
    }
}