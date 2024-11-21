package com.jeluchu.core.messages

sealed class ErrorMessages(val message: String) {
    data class Custom(val error: String) : ErrorMessages(error)
    data object NotFound : ErrorMessages("Nyaaaaaaaan! This request has not been found by our alpaca-neko")
    data object AnimeNotFound : ErrorMessages("This malId is not in our database")
    data object InvalidMalId : ErrorMessages("The provided id of malId is invalid")
    data object InvalidInput : ErrorMessages("Invalid input provided")
    data object UnauthorizedMongo : ErrorMessages("Check the MongoDb Connection String to be able to correctly access this request.")
}