package eyedentify.email.extensions

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String?.toMd5(): String {
    if (this.isNullOrEmpty()) {
        return ""
    }
    val md5: MessageDigest
    return try {
        md5 = MessageDigest.getInstance("MD5")
        val bytes = md5.digest(this.toByteArray())
        bytesToHex(bytes)
    } catch (e: NoSuchAlgorithmException) {
        ""
    }
}

fun bytesToHex(byteArray: ByteArray): String {
    var result = ""
    var tmp: String
    for (byte in byteArray) {
        tmp = Integer.toHexString(byte.toInt() and 0xFF)
        if (tmp.length == 1) {
            result += "0"
        }
        result += tmp
    }
    return result
}
