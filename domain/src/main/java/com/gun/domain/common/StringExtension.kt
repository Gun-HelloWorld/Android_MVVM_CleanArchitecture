package com.gun.domain.common

import java.math.BigInteger
import java.security.MessageDigest

fun String.toMd5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}