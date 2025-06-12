package com.jeluchu.features.news.mappers

import com.jeluchu.core.extensions.getListSafe
import com.jeluchu.core.extensions.getStringSafe
import com.jeluchu.core.extensions.parseRssDate
import com.jeluchu.features.news.models.NewEntity
import com.prof18.rssparser.model.RssChannel
import org.bson.Document

fun RssChannel.toNews(
    source: String,
    list: MutableList<NewEntity>
) = list.apply{
    items.forEach { item ->
        add(
            NewEntity(
                source = source,
                sourceDescription = description.orEmpty(),
                link = item.link.orEmpty(),
                title = item.title.orEmpty(),
                date = item.pubDate?.parseRssDate().orEmpty(),
                description = item.description.orEmpty(),
                content = item.content.orEmpty(),
                image = item.image.orEmpty(),
                categories = item.categories
            )
        )
    }
}

fun documentToNewsEntity(doc: Document) = NewEntity(
    source = doc.getStringSafe("source"),
    sourceDescription = doc.getStringSafe("sourceDescription"),
    link = doc.getStringSafe("link"),
    title = doc.getStringSafe("title"),
    date = doc.getStringSafe("date"),
    description = doc.getStringSafe("description"),
    content = doc.getStringSafe("content"),
    image = doc.getStringSafe("image"),
    categories = doc.getListSafe<String>("categories")
)