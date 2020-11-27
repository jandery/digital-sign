package se.refur.digital_sign

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DsaKeyPairSigningTest {

    private val stringToSign = "This is a string to sign"

    private val firstKeyPair = DsaKeyPairSigning.generate()
    private val secondKeyPair = DsaKeyPairSigning.generate()

    @Test
    fun `validate signature, use same public key, true`() {
        val signature = firstKeyPair.createSignature(stringToSign)
        Assertions.assertTrue(firstKeyPair.validateSignature(stringToSign, signature))
        Assertions.assertTrue(DsaKeyPairSigning.validate(firstKeyPair.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, use different public key, false`() {
        val signature = secondKeyPair.createSignature(stringToSign)
        Assertions.assertFalse(firstKeyPair.validateSignature(stringToSign, signature))
        Assertions.assertFalse(DsaKeyPairSigning.validate(firstKeyPair.publicKey, stringToSign, signature))
    }
}