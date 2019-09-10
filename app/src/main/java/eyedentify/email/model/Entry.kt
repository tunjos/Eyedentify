package eyedentify.email.model

import java.io.Serializable

data class Entry(
    val aboutMe: String? = null,
    val currentLocation: String? = null,
    val displayName: String? = null,
    val hash: String? = null,
    val id: String? = null,
    val name: Name? = null,
    val photos: List<Photo>? = null,
    val preferredUsername: String? = null,
    val profileUrl: String? = null,
    val requestHash: String? = null,
    val thumbnailUrl: String? = null,
    val urls: List<Url>? = null,
    var email: String? = null
) : Serializable
