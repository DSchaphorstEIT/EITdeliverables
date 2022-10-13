package com.example.dschaphorst_p5_dnd.util

import android.view.View

/**
 * These are the possible classes that a character can be. Each spell should hold a list
 * of classes that are able to use that spell.
 */
enum class Classes5e(var text: String) {
    BARBARIAN("Barbarian"),
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    FIGHTER("Fighter"),
    MONK("Monk"),
    PALADIN("Paladin"),
    RANGER("Ranger"),
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard"),
    OTHER("Other");

    override fun toString(): String {
        return text
    }
}