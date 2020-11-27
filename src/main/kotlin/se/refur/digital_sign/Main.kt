package se.refur.digital_sign

import java.util.*

/**
 * The purpose of this
 */
fun main() {

    val rsaSigning = RsaKeyPairSigning.generate()
    testSigning(rsaSigning)

    val dsaSigning = DsaKeyPairSigning.generate()
    testSigning(dsaSigning)

    // Save to file
    // rsaSigning.writeToFile("~/keys")

    // Encrypt with public key
    testEncryption(rsaSigning)

}

fun testSigning(signing: IKeyPairSigning) {
    val stringToSign = "This is a not so long string to sign"
    val signature = signing.createSignature(stringToSign)
    val isSignValid = signing.validateSignature(stringToSign, signature)
    "Is signature valid? $isSignValid".println()
}

fun testEncryption(rsaKeyPairSigning: RsaKeyPairSigning) {
    "This is a not so long string to encrypt"
            .let { rsaKeyPairSigning.encrypt(it) }
            .also { String(Base64.getEncoder().encode(it)).println() }
            .let { rsaKeyPairSigning.decrypt(it) }
            .also { it.println() }
}


private fun String.println() = println(this)