package com.jeluchu.core.messages

sealed class GeneralMessages(val message: String) {
    data class Custom(val info: String) : GeneralMessages(info)
    data object DocumentationReference : GeneralMessages("More information on the Aruppi API can be found in the official documentation at: http://0.0.0.0:8080/swagger")
}