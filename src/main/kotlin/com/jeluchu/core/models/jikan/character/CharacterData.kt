package fordelete

import com.jeluchu.core.models.jikan.anime.Images
import com.jeluchu.features.rankings.models.CharacterTopEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterData(
    @SerialName("mal_id")
    val malId: Int? = 0,

    @SerialName("about")
    val about: String? = "",

    @SerialName("favorites")
    val favorites: Int? = 0,

    @SerialName("images")
    val images: Images? = Images(),

    @SerialName("name")
    val name: String? = "",

    @SerialName("name_kanji")
    val nameKanji: String? = "",

    @SerialName("nicknames")
    val nicknames: List<String>? = emptyList(),

    @SerialName("url")
    val url: String? = ""
) {
    companion object {
        fun CharacterData.toCharacterTopEntity(
            page: Int,
            top: String
        ) = CharacterTopEntity(
            malId = malId,
            image = images?.jpg?.generic.orEmpty(),
            name = name,
            nameKanji = nameKanji,
            top = top,
            page = page
        )
    }
}