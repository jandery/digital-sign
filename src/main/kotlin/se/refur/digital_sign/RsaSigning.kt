package se.refur.digital_sign

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey

/**
 * The purpose of this class is to implement RSA, (Rivest, Shamir, Adleman) digital signing
 */
class RsaSigning(override val publicKey: PublicKey, private val privateKey: PrivateKey) : AbstractSigning(privateKey) {
    constructor(keyPair: KeyPair) : this(keyPair.public, keyPair.private)

    override val algorithmEnum: AlgorithmEnum = AlgorithmEnum.RSA


    companion object : IKeyPairGeneration, IValidation {
        override val algorithmEnum = AlgorithmEnum.RSA

        override fun generate(): RsaSigning =
                RsaSigning(generateKeyPair())

        override fun readFromFile(filePath: String): RsaSigning = RsaSigning(
                publicKey = readPublicKey(filePath),
                privateKey = readPrivateKey(filePath))

    }
}