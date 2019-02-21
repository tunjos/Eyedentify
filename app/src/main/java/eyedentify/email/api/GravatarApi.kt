package eyedentify.email.api

import eyedentify.email.model.Profile
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GravatarApi {

    @GET("/avatar/{hash}s=$DEFAULT_AVATAR_SIZE&r={rating}&d=mp")
    fun avatar(@Path("hash") hash: String, @Path("rating") rating: String = DEFAULT_AVATAR_RATING): Single<Profile>

    @GET("/{hash}.json")
    fun profile(@Path("hash") hash: String)

    companion object {
        const val DEFAULT_AVATAR_SIZE = 100
        const val DEFAULT_AVATAR_RATING = "g"
    }
}
