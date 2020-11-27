package se.refur.digital_sign

import java.security.PublicKey


interface ISigning {
    val algorithmEnum: AlgorithmEnum
    val publicKey: PublicKey

    fun createSignature(stringToSign: String): ByteArray

    fun writeToFile(filePath: String)

}



