package se.refur.digital_sign

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey

/**
 * The purpose of this class is to implement RSA, (Rivest, Shamir, Adleman) digital signing
 */
class RsaImpl(override val publicKey: PublicKey, privateKey: PrivateKey) : AbstractSigning(privateKey) {
    constructor(keyPair: KeyPair) : this(keyPair.public, keyPair.private)

    override val algorithmEnum: AlgorithmEnum = AlgorithmEnum.RSA

    companion object : IValidation {
        override val algorithmEnum = AlgorithmEnum.RSA
    }
}
