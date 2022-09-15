package com.eitcat.dschaphorst_p3_music.util


class NullResponseFromServer(message: String) : Exception(message)
class FailureResponseFromServer(message: String?) : Exception(message)