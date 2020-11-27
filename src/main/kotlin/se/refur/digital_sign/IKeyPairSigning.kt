package se.refur.digital_sign

interface IKeyPairSigning {

    val algorithm: String

    fun createSignature(stringToSign: String): ByteArray

    fun validateSignature(stringToValidate: String, signing: ByteArray): Boolean

    fun writeToFile(filePath: String)

}