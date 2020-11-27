package se.refur.digital_sign

import java.lang.Exception
import java.security.PublicKey
import java.security.Signature
import java.util.*

/**
 * The purpose of this interface is to validate a signed text
 */
interface IValidation {
    val algorithmEnum: AlgorithmEnum

    fun validateSignature(publicKey: Any, stringToValidate: String, signature: ByteArray): Boolean =
            Signature.getInstance(algorithmEnum.signAlgorithm)
                    .also { it.initVerify(castKey(publicKey)) }
                    .also { it.update(stringToValidate.toByteArray()) }
                    .verify(signature)


    private fun castKey(publicKey: Any): PublicKey = when (publicKey) {
        is PublicKey -> publicKey
        is String -> algorithmEnum.readPublicKey(Base64.getDecoder().decode(publicKey))
        is ByteArray -> algorithmEnum.readPublicKey(publicKey)
        else -> throw Exception("")
    }
}