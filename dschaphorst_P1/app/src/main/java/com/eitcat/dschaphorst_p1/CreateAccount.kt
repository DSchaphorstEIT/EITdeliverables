package com.eitcat.dschaphorst_p1

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import com.eitcat.dschaphorst_p1.databinding.ActivityCreateAccountBinding

class CreateAccount : AppCompatActivity() {

    private val binding by lazy {
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }

    private val sharedPref: SharedPreferences by lazy {
        baseContext.getSharedPreferences("SavedEmail", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inputFocusListener()
    }

    override fun onResume() {
        super.onResume()
        if (binding.btnCreate.isClickable){
            binding.btnCreate.setOnClickListener{
                sharedPref.edit().apply{
                    putString(binding.etEmail.text.toString(), binding.etEmail.text.toString())
                    apply()
                }
                Toast.makeText(baseContext, "Account created successfully.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        binding.btnBack.setOnClickListener{ finish() }
    }

    private fun inputFocusListener(){
        var validEmail = false; var validPassword = false
        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                validEmail = validateEmail()
                toggleCreateBtn(validEmail && validPassword)
            }
        }
        binding.etPassword.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                validPassword = validatePassword()
                toggleCreateBtn(validEmail && validPassword)
            }
        }
        binding.etConfirm.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                validPassword = validatePassword()
                toggleCreateBtn(validEmail && validPassword)
            }
        }
    }

    private fun validateEmail (): Boolean {
        val emailText = binding.etEmail.text.toString()
        return if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            binding.etEmail.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            resources.getString(R.string.email_invalid).apply {
                binding.etEmail.error = this
                binding.tvEmailERROR.text = this
            }
            toggleEmailError(true)
            false
        } else if (sharedPref.contains(emailText)) {
            binding.etEmail.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            resources.getString(R.string.email_exists).apply {
                binding.etEmail.error = this
                binding.tvEmailERROR.text = this
            }
            toggleEmailError(true)
            false
        } else {
            binding.etEmail.background = ContextCompat.getDrawable(baseContext, R.drawable.accepted_box)
            "".apply { binding.tvEmailERROR.text = this }
            toggleEmailError(false)
            true
        }
    }

    private fun validatePassword (): Boolean {
        val passText = binding.etPassword.text.toString()
        val confText = binding.etConfirm.text.toString()
        return if (!passText.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z]).*\$".toRegex())) {
            binding.etPassword.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            binding.etConfirm.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            resources.getString(R.string.pass_invalid).apply {
                binding.etPassword.error = this
                binding.tvPasswordERROR.text = this
            }
            togglePasswordError(true)
            false
        } else if (passText != confText) {
            binding.etPassword.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            binding.etConfirm.background = ContextCompat.getDrawable(baseContext, R.drawable.error_box)
            resources.getString(R.string.pass_mismatch).apply {
                binding.etConfirm.error = this
                binding.tvPasswordERROR.text = this
            }
            togglePasswordError(true)
            false
        } else {
            binding.etPassword.background = ContextCompat.getDrawable(baseContext, R.drawable.accepted_box)
            binding.etConfirm.background = ContextCompat.getDrawable(baseContext, R.drawable.accepted_box)
            binding.etPassword.error = null
            binding.etConfirm.error = null
            "".apply { binding.tvPasswordERROR.text = this }
            togglePasswordError(false)
            true
        }
    }

    private fun toggleEmailError(enabled: Boolean){
        if (enabled){
            binding.tvEmailERROR.visibility = View.VISIBLE
            binding.etEmail.updateLayoutParams<ConstraintLayout.LayoutParams> {
                bottomToTop = binding.tvEmailERROR.id
            }
            binding.tvPassword.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = binding.tvEmailERROR.id
            }
        } else {
            binding.tvEmailERROR.visibility = View.GONE
            binding.etEmail.updateLayoutParams<ConstraintLayout.LayoutParams> {
                bottomToTop = binding.tvPassword.id
            }
            binding.tvPassword.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = binding.etEmail.id
            }
        }
    }

    private fun togglePasswordError(enabled: Boolean){
        if (enabled){
            binding.tvPasswordERROR.visibility = View.VISIBLE
            binding.etConfirm.updateLayoutParams<ConstraintLayout.LayoutParams> {
                bottomToTop = binding.tvPasswordERROR.id
            }
//            binding.tvRequirements.updateLayoutParams<ConstraintLayout.LayoutParams> {
//                topToBottom = binding.tvPasswordERROR.id
//            }
        } else {
            binding.tvPasswordERROR.visibility = View.GONE
            binding.etConfirm.updateLayoutParams<ConstraintLayout.LayoutParams> {
                bottomToTop = binding.tvRequirements.id
            }
//            binding.tvRequirements.updateLayoutParams<ConstraintLayout.LayoutParams> {
//                topToBottom = binding.etConfirm.id
//            }
        }
    }

    private fun toggleCreateBtn(enabled: Boolean){
        binding.btnCreate.isClickable = enabled
        binding.btnCreate.alpha = if(enabled) 1F else 0.5F
    }
}