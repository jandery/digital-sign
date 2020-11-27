package se.refur.digital_sign

import java.security.PublicKey
import java.security.Signature

/**
 * The purpose of this interface is to validate a signed text
 */
interface IValidation {
    val algorithmEnum: AlgorithmEnum

    fun validateSignature(publicKey: PublicKey, stringToValidate: String, signature: ByteArray): Boolean =
            Signature.getInstance(algorithmEnum.signAlgorithm)
                    .also { it.initVerify(publicKey) }
                    .also { it.update(stringToValidate.toByteArray()) }
                    .verify(signature)

}