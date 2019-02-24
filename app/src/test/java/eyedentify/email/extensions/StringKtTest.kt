package eyedentify.email.extensions

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StringKtTest {

    @Test
    fun hashesEmailToMd5Correctly() {
        val email = "gravatar@gravatar.com"
        val md5Hash = email.toMd5()

        assertEquals("2b85c1e9cb2440fb72093c0c8db737e2", md5Hash)
    }

    @Test
    fun emptyStringToMd5ReturnsEmptyString() {
        val email = ""
        val md5Hash = email.toMd5()

        assertEquals("", md5Hash)
    }

    @Test
    fun nullStringToMd5ReturnsEmptyString() {
        val email = null
        val md5Hash = email.toMd5()

        assertEquals("", md5Hash)
    }
}
