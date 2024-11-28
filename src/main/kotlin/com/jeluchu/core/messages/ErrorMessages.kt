package com.jeluchu.core.messages

import com.jeluchu.core.utils.Day

sealed class ErrorMessages(val message: String) {
    data object NotFound : ErrorMessages("Nyaaaaaaaan! This request has not been found by our alpaca-neko")
    data object AnimeNotFound : ErrorMessages("This malId is not in our database")
    data object InvalidMalId : ErrorMessages("The provided id of malId is invalid")
    data object InvalidDay : ErrorMessages("Invalid 'day' parameter. Valid values are: ${Day.entries.joinToString(", ") { it.name.lowercase() }}")
    data object InvalidInput : ErrorMessages("Invalid input provided")
    data object UnauthorizedMongo : ErrorMessages("Check the MongoDb Connection String to be able to correctly access this request.")
}