package se.refur.digital_sign

import java.io.File
import java.security.*

/**
 * The purpose of this interface is to generate KeyPair for digital signing
 */
interface IKeyPairGeneration {
    val algorithmEnum: AlgorithmEnum

    /**
     * Function to generate a Public/Private key-pair for argument algorithm
     */
    fun generateKeyPair(): KeyPair =
            SecureRandom()
                    .let { secureRandom ->
                        KeyPairGenerator.getInstance(algorithmEnum.generateAlgorithm)
                                .also { keyPairGenerator -> keyPairGenerator.initialize(2048, secureRandom) }
                                .generateKeyPair()
                    }

    /**
     * reading a public key from file
     */
    fun readPublicKey(filePath: String): PublicKey =
            algorithmEnum.readPublicKey(File("$filePath.public").readBytes())

    /**
     * reading private key from file
     */
    fun readPrivateKey(filePath: String): PrivateKey =
            algorithmEnum.readPrivateKey(File("$filePath.private").readBytes())

    /**
     * Generate new public/private key for digital signing
     */
    fun generate(): ISigning

    /**
     * reading key-pair from file
     */
    fun readFromFile(filePath: String): ISigning

}