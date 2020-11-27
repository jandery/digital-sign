package se.refur.digital_sign

import java.security.PublicKey


/**
 * The purpose of this file is functions for digital signature
 */
interface ISigning {
    val algorithmEnum: AlgorithmEnum
    val publicKey: PublicKey

    fun createSignature(stringToSign: String): ByteArray
}



