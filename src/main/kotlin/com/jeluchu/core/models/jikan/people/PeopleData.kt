package com.jeluchu.core.models.jikan.people

import com.jeluchu.core.models.jikan.anime.Images
import com.jeluchu.features.rankings.models.PeopleTopEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleData(
    @SerialName("mal_id")
    val malId: Int? = 0,

    @SerialName("about")
    val about: String? = "",

    @SerialName("alternate_names")
    val alternateNames: List<String>? = emptyList(),

    @SerialName("birthday")
    val birthday: String? = "",

    @SerialName("family_name")
    val familyName: String? = "",

    @SerialName("favorites")
    val favorites: Int? = 0,

    @SerialName("given_name")
    val givenName: String? = "",

    @SerialName("images")
    val images: Images? = Images(),

    @SerialName("name")
    val name: String? = "",

    @SerialName("url")
    val url: String? = "",

    @SerialName("website_url")
    val websiteUrl: String? = ""
) {
    companion object {
        fun PeopleData.toPeopleTopEntity(
            page: Int,
            top: String
        ) = PeopleTopEntity(
            malId = malId,
            image = images?.jpg?.generic.orEmpty(),
            name = name,
            givenName = givenName,
            familyName = familyName,
            birthday = birthday,
            top = top,
            page = page
        )
    }
}