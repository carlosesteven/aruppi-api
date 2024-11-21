package com.jeluchu.core.configuration

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import io.ktor.server.application.*
import io.ktor.server.config.*

/**
 * Establishes connection with a MongoDB database.
 *
 * The following configuration properties (in application.yaml/application.conf) can be specified:
 * * `db.mongo.connectionStrings` connectionStrings for your MongoDB
 * * `db.mongo.database.name` name of the database
 *
 * IMPORTANT NOTE: in order to make MongoDB connection working, you have to start a MongoDB server first.
 * See the instructions here: https://www.mongodb.com/docs/manual/administration/install-community/
 * all the paramaters above
 *
 * @returns [MongoDatabase] instance
 * */
fun Application.connectToMongoDB(): MongoDatabase {
    val connectionStrings = environment.config.tryGetString("db.mongo.connectionStrings") ?: "mongodb://"
    val databaseName = environment.config.tryGetString("db.mongo.database.name") ?: "aruppi"

    val mongoClient = MongoClients.create(connectionStrings)
    val database = mongoClient.getDatabase(databaseName)

    monitor.subscribe(ApplicationStopped) {
        mongoClient.close()
    }

    return database
}