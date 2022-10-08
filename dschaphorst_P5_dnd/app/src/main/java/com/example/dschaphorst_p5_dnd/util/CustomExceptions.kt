package com.example.dschaphorst_p5_dnd.util

/**
 * Set of Custom Exception classes used to better control Exception responses.
 *
 * @constructor
 * Message string
 *
 * @param message The message of the Exception
 */

class NullResponseFromServer(message: String) : Exception(message)
class FailureResponseFromServer(message: String?) : Exception(message)
class InvalidApiCaller(message: String?) : Exception(message)