package com.example.dschaphorst_p5_dnd.util

import android.util.Log
import android.view.View
import com.example.dschaphorst_p5_dnd.databinding.FragmentSpellEditorBinding
import kotlin.properties.Delegates

private const val TAG = "SpellValidator"

object SpellValidator {
    fun isValid(binding: FragmentSpellEditorBinding): Boolean {
        var isValid = true
        val spellName = binding.spellName.text.toString()
        var spellLevel by Delegates.notNull<Int>()
        try {
            spellLevel = Integer.parseInt(binding.spellLevel.text.toString())
        } catch (e: Exception) {
            Log.d(TAG, "isValid: ${e.localizedMessage}")
            isValid = false
            spellLevel = -1
        }
        if (spellName == "") {
            isValid = false
            binding.spellName.error = "Please provide a name for the spell."
        } else {
            binding.spellName.error = null
        }
        if (spellLevel < 0 || spellLevel > 9) {
            isValid = false
            binding.spellLevel.error = "Spell levels can only be between 0 and 9"
        } else {
            binding.spellLevel.error = null
        }
        if (!binding.verbal.isChecked && !binding.somatic.isChecked && !binding.materials.isChecked) {
            isValid = false
            binding.componentsError.visibility = View.VISIBLE
        } else {
            binding.componentsError.visibility = View.GONE
        }
        return isValid
    }

    fun levelOrdinal(lvl: Int): String = when (lvl) {
        0 -> "Cantrip"
        1 -> "1st-level"
        2 -> "2nd-level"
        3 -> "3rd-level"
        else -> lvl.toString() + "th-level"
    }

    fun componentsString(v: Boolean, s: Boolean, m: Boolean): String {
        var comps = ""
        if (v) comps += "V"
        if (v && s || m) comps += ", "
        if (s) comps += "S"
        if (s && m) comps += ", "
        if (m) comps += "M"
        return comps
    }
}