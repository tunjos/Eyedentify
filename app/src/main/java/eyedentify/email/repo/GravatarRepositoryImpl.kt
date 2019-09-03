package eyedentify.email.repo

import eyedentify.email.api.GravatarApi
import eyedentify.email.extensions.toMd5
import eyedentify.email.model.Profile
import io.reactivex.Single

class GravatarRepositoryImpl(private val gravatarApi: GravatarApi) : GravatarRepository {

    override fun getProfile(email: String): Single<Profile> {
        return gravatarApi.profile(email.toMd5())
    }
}
