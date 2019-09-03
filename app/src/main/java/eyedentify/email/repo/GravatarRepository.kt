package eyedentify.email.repo

import eyedentify.email.model.Profile
import io.reactivex.Single

interface GravatarRepository {

    fun getProfile(email: String): Single<Profile>
}
