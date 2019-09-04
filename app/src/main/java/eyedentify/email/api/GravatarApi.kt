package eyedentify.email.api

import eyedentify.email.model.Profile
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GravatarApi {

    @GET("/{hash}.json")
    fun profile(@Path("hash") hash: String): Single<Profile>

    companion object {
        const val HTTP_404 = 200

        private const val DEFAULT_AVATAR_SIZE = 200
        private const val DEFAULT_AVATAR_RATING = "g"

        fun generateThumbnailUrl(thumbnailUrl: String, rating: String = DEFAULT_AVATAR_RATING) =
            "$thumbnailUrl?s=$DEFAULT_AVATAR_SIZE&r=$rating&d=mp"

    }
}
