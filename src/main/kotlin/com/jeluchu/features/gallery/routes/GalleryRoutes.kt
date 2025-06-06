package com.jeluchu.features.gallery.routes

import com.jeluchu.core.extensions.getToJson
import com.jeluchu.core.utils.Routes
import com.jeluchu.features.gallery.services.GalleryService
import com.jeluchu.features.news.services.NewsService
import com.mongodb.client.MongoDatabase
import io.ktor.server.routing.*

fun Route.galleryEndpoints(
    mongoDatabase: MongoDatabase,
    service: GalleryService = GalleryService(mongoDatabase)
) = route(Routes.GALLERY) {
    route(Routes.LAST_POST) {
        getToJson { service.getLastPosts(call) }
    }

    getToJson { service.getQueryImages(call) }
}