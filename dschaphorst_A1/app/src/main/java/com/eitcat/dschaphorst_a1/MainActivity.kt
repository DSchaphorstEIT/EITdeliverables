package com.eitcat.dschaphorst_a1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mCount = 0
    private lateinit var mShowCount: TextView
    companion object {
        const val NUMKEY = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mShowCount = findViewById(R.id.show_count)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NUMKEY, mCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mCount = savedInstanceState.getInt(NUMKEY)
        Log.d("MainActivity", "Count is $mCount")
        mShowCount?.setText(Integer.toString(mCount)) ?: findViewById(R.id.show_count)
    }

    fun showToast(view: View) {
        val toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        toast.show()
    }
    fun countUp(view: View) {
        mCount++
        mShowCount?.setText(Integer.toString(mCount)) ?: findViewById(R.id.show_count)
    }


}