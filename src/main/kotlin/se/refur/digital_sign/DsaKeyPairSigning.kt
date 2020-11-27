package se.refur.digital_sign

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey

/**
 * The purpose of this class is to implement DSA, Digital Signature Algorithm
 */
class DsaKeyPairSigning(override val publicKey: PublicKey, privateKey: PrivateKey) : AbstractKeyPairSigning(privateKey) {
    constructor(keyPair: KeyPair) : this(keyPair.public, keyPair.private)

    override val algorithm: String = hashAlgorithm

    companion object {
        private const val mainAlgorithm: String = "DSA"
        private const val hashAlgorithm: String = "SHA256withDSA"

        fun generate(): DsaKeyPairSigning =
                DsaKeyPairSigning(generate(mainAlgorithm))

        fun validate(publicKey: PublicKey, stringToValidate: String, signature: ByteArray): Boolean = validateSignature(
                hashAlgorithm, publicKey, stringToValidate, signature)

        fun readFromFile(filePath: String): DsaKeyPairSigning = DsaKeyPairSigning(
                publicKey = readPublicKey(filePath, mainAlgorithm),
                privateKey = readPrivateKey(filePath, mainAlgorithm))
    }
}