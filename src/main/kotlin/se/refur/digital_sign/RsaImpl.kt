package se.refur.digital_sign

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher

/**
 * The purpose of this class is to implement RSA, (Rivest, Shamir, Adleman) digital signing
 */
class RsaImpl(override val publicKey: PublicKey, private val privateKey: PrivateKey) : AbstractSigning(privateKey) {
    constructor(keyPair: KeyPair) : this(keyPair.public, keyPair.private)

    override val algorithmEnum: AlgorithmEnum = AlgorithmEnum.RSA

    fun decrypt(bytesToDecrypt: ByteArray): String {
        val decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey)
        return String(decryptCipher.doFinal(bytesToDecrypt), charset("UTF-8"))
    }

    companion object : IValidation {
        override val algorithmEnum = AlgorithmEnum.RSA

        fun encrypt(publicKey: PublicKey, stringToEncrypt: String): ByteArray {
            val encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey)
            return encryptCipher.doFinal(stringToEncrypt.toByteArray())
        }
    }
}
