package eyedentify.email.model

import java.io.Serializable

data class Name(
    val familyName: String? = null,
    val formatted: String? = null,
    val givenName: String? = null
) : Serializable
