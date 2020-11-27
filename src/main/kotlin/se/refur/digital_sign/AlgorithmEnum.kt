package se.refur.digital_sign

import java.io.File
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

/**
 * The purpose of this generate KeyPair for digital signing
 * @property RSA (Rivest, Shamir, Adleman)
 * @property DSA Digital Signature Algorithm
 */
enum class AlgorithmEnum(private val generateAlgorithm: String, val signAlgorithm: String) {
    RSA(generateAlgorithm = "RSA", signAlgorithm = "SHA256withRSA") {
        override fun generate(): ISigning = RsaImpl(generateKeyPair())

        override fun readFromFile(filePath: String): ISigning = filePath.let {
            RsaImpl(publicKey = readPublicKey(it), privateKey = readPrivateKey(it))
        }
    },
    DSA(generateAlgorithm = "DSA", signAlgorithm = "SHA256withDSA") {
        override fun generate(): ISigning = DsaImpl(generateKeyPair())

        override fun readFromFile(filePath: String): ISigning = filePath.let {
            DsaImpl(publicKey = readPublicKey(it), privateKey = readPrivateKey(it))
        }
    };

    /**
     * Generate new public/private key for digital signing
     */
    abstract fun generate(): ISigning

    /**
     * reading key-pair from file
     */
    abstract fun readFromFile(filePath: String): ISigning

    /**
     * writing key-pair to file
     */
    fun writeToFile(filePath: String, publicKey: PublicKey, privateKey: PrivateKey) {
        File("$filePath.public").writeBytes(publicKey.encoded)
        File("$filePath.private").writeBytes(privateKey.encoded)
    }

    fun readPublicKey(byteArray: ByteArray): PublicKey =
            KeyFactory
                    .getInstance(generateAlgorithm)
                    .generatePublic(X509EncodedKeySpec(byteArray))

    fun readPrivateKey(byteArray: ByteArray): PrivateKey =
            KeyFactory
                    .getInstance(generateAlgorithm)
                    .generatePrivate(PKCS8EncodedKeySpec(byteArray))

    /**
     * reading a public key from file
     */
    fun readPublicKey(filePath: String): PublicKey =
            readPublicKey(File("$filePath.public").readBytes())

    /**
     * reading private key from file
     */
    fun readPrivateKey(filePath: String): PrivateKey =
            readPrivateKey(File("$filePath.private").readBytes())

    /**
     * Function to generate a Public/Private key-pair for algorithm
     */
    fun generateKeyPair(): KeyPair =
            SecureRandom()
                    .let { secureRandom ->
                        KeyPairGenerator.getInstance(generateAlgorithm)
                                .also { keyPairGenerator -> keyPairGenerator.initialize(2048, secureRandom) }
                                .generateKeyPair()
                    }
}