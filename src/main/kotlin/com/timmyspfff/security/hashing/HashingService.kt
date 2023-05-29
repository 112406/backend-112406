package com.timmyspfff.security.hashing

interface HashingService {
    fun generatedSaltedHash(value: String, saltLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}