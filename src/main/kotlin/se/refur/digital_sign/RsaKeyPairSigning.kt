package se.refur.digital_sign

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher

/**
 * The purpose of this class is to implement RSA, (Rivest, Shamir, Adleman) digital signing
 */
class RsaKeyPairSigning(override val publicKey: PublicKey, private val privateKey: PrivateKey) : AbstractKeyPairSigning(privateKey) {
    constructor(keyPair: KeyPair) : this(keyPair.public, keyPair.private)

    override val algorithm: String = hashAlgorithm

    fun encrypt(stringToEncrypt: String): ByteArray {
        val encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return encryptCipher.doFinal(stringToEncrypt.toByteArray())
    }

    fun decrypt(bytesToDecrypt: ByteArray): String {
        val decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey)
        return String(decryptCipher.doFinal(bytesToDecrypt), charset("UTF-8"))
    }

    companion object {
        private const val mainAlgorithm: String = "RSA"
        private const val hashAlgorithm: String = "SHA256withRSA"

        fun generate(): RsaKeyPairSigning =
                RsaKeyPairSigning(generate(mainAlgorithm))

        fun validate(publicKey: PublicKey, stringToValidate: String, signature: ByteArray): Boolean = validateSignature(
                hashAlgorithm, publicKey, stringToValidate, signature)

        fun readFromFile(filePath: String): RsaKeyPairSigning = RsaKeyPairSigning(
                publicKey = readPublicKey(filePath, mainAlgorithm),
                privateKey = readPrivateKey(filePath, mainAlgorithm))
    }
}