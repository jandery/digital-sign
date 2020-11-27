package se.refur.digital_sign

import java.io.File
import java.security.*

/**
 * The purpose of this abstract class is to implement functions for digital signing
 */
abstract class AbstractSigning(private val privateKey: PrivateKey) : ISigning {

    abstract override val publicKey: PublicKey

    override fun createSignature(stringToSign: String): ByteArray =
            Signature.getInstance(algorithmEnum.signAlgorithm)
                    .also { it.initSign(privateKey) }
                    .also { it.update(stringToSign.toByteArray()) }
                    .sign()

    override fun writeToFile(filePath: String) {
        File("$filePath.public").writeBytes(publicKey.encoded)
        File("$filePath.private").writeBytes(privateKey.encoded)
    }

}