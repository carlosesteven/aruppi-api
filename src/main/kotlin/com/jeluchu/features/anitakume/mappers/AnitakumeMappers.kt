package com.jeluchu.features.anitakume.mappers

import com.jeluchu.core.extensions.getStringSafe
import com.jeluchu.core.extensions.parseRssDate
import com.jeluchu.features.anitakume.models.AnitakumeEntity
import com.prof18.rssparser.model.RssChannel
import org.bson.Document

fun RssChannel.toPodcast(
    list: MutableList<AnitakumeEntity>
) = list.apply{
    items.forEach { item ->
        add(
            AnitakumeEntity(
                image = item.itunesItemData?.image.orEmpty(),
                title = item.title?.substringAfter(" · ").orEmpty(),
                description = item.description.orEmpty(),
                date = item.pubDate?.parseRssDate().orEmpty(),
                link = item.link.orEmpty(),
                audio = item.audio.orEmpty(),
                duration = item.itunesItemData?.duration.orEmpty()
            )
        )
    }
}

fun documentToAnitakumeEntity(doc: Document) = AnitakumeEntity(
    image = doc.getStringSafe("image"),
    title = doc.getStringSafe("title"),
    description = doc.getStringSafe("description"),
    date =  doc.getStringSafe("date"),
    link = doc.getStringSafe("link"),
    audio = doc.getStringSafe("audio"),
    duration = doc.getStringSafe("duration")
)