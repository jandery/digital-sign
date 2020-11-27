package se.refur.digital_sign

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

/**
 * The purpose of this
 */
enum class AlgorithmEnum(val generateAlgorithm: String, val signAlgorithm: String) {
    RSA("RSA", "SHA256withRSA"),
    DSA("DSA", "SHA256withDSA");

    fun readPublicKey(byteArray: ByteArray): PublicKey {
        val ks = X509EncodedKeySpec(byteArray)
        val kf = KeyFactory.getInstance(generateAlgorithm)
        return kf.generatePublic(ks)
    }

    fun readPrivateKey(byteArray: ByteArray): PrivateKey {
        val ks = PKCS8EncodedKeySpec(byteArray)
        val kf = KeyFactory.getInstance(generateAlgorithm)
        return kf.generatePrivate(ks)
    }
}