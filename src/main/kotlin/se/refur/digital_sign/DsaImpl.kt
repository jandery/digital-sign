package se.refur.digital_sign

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey

/**
 * The purpose of this class is to implement DSA, Digital Signature Algorithm
 */
class DsaImpl(override val publicKey: PublicKey, privateKey: PrivateKey) : AbstractSigning(privateKey) {
    constructor(keyPair: KeyPair) : this(keyPair.public, keyPair.private)

    override val algorithmEnum: AlgorithmEnum = AlgorithmEnum.DSA

    companion object : IValidation {
        override val algorithmEnum = AlgorithmEnum.DSA
    }
}
