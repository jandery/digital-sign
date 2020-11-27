package se.refur.digital_sign

import java.io.File
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

/**
 * The purpose of this abstract class is to implement functions for digital signing
 */
abstract class AbstractKeyPairSigning(private val privateKey: PrivateKey) : IKeyPairSigning {

    abstract val publicKey: PublicKey

    override fun createSignature(stringToSign: String): ByteArray =
            Signature.getInstance(algorithm)
                    .also { it.initSign(privateKey) }
                    .also { it.update(stringToSign.toByteArray()) }
                    .sign()

    override fun validateSignature(stringToValidate: String, signing: ByteArray): Boolean =
            validateSignature(algorithm, publicKey, stringToValidate, signing)

    override fun writeToFile(filePath: String) {
        File("$filePath.public").writeBytes(publicKey.encoded)
        File("$filePath.private").writeBytes(privateKey.encoded)
    }


    companion object {
        /**
         * Function to generate a Public/Private key-pair for argument algorithm
         */
        fun generate(algorithm: String): KeyPair =
                SecureRandom()
                        .let { secureRandom ->
                            KeyPairGenerator.getInstance(algorithm)
                                    .also { keyPairGenerator -> keyPairGenerator.initialize(2048, secureRandom) }
                                    .generateKeyPair()
                        }

        internal fun validateSignature(algorithm: String, publicKey: PublicKey, stringToValidate: String, signature: ByteArray): Boolean =
                Signature.getInstance(algorithm)
                        .also { it.initVerify(publicKey) }
                        .also { it.update(stringToValidate.toByteArray()) }
                        .verify(signature)


        internal fun readPublicKey(filePath: String, algorithm: String): PublicKey {
            val publicKey: ByteArray = File("$filePath.public").readBytes()
            val ks = X509EncodedKeySpec(publicKey)
            val kf = KeyFactory.getInstance(algorithm)
            return kf.generatePublic(ks)
        }

        internal fun readPrivateKey(filePath: String, algorithm: String): PrivateKey {
            val privateKey: ByteArray = File("$filePath.private").readBytes()
            val ks = PKCS8EncodedKeySpec(privateKey)
            val kf = KeyFactory.getInstance(algorithm)
            return kf.generatePrivate(ks)
        }
    }
}