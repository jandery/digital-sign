package se.refur.digital_sign

import java.security.PublicKey
import java.util.*


/**
 * The purpose of this file is functions for digital signature
 */
interface ISigning {
    val algorithmEnum: AlgorithmEnum
    val publicKey: PublicKey

    fun createSignature(stringToSign: String): ByteArray

    fun createBase64Signature(stringToSign: String): String =
            Base64.getEncoder().encodeToString(createSignature(stringToSign))
}



