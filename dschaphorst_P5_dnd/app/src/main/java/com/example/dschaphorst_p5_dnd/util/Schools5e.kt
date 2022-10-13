package com.example.dschaphorst_p5_dnd.util

enum class Schools5e (var text: String) {
    ABJURATION("Abjuration"),
    CONJURATION("Conjuration"),
    DIVINATION("Divination"),
    ENCHANTMENT("Enchantment"),
    EVOCATION("Evocation"),
    ILLUSION("Illusion"),
    NECROMANCY("Necromancy"),
    TRANSMUTATION("Transmutation");

    override fun toString(): String {
        return text
    }
}