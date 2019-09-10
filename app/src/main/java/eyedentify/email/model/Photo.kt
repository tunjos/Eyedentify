package eyedentify.email.model

import java.io.Serializable

data class Photo(
    val type: String? = null,
    val value: String? = null
) : Serializable
