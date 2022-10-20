package com.example.dschaphorst_modiscc_calendar.util

enum class OrderTypes(var text: String) {
    PICKUP("Pickup"),
    CURBSIDE("Curbside"),
    DELIVERY("Delivery");

    override fun toString(): String {
        return text
    }
}