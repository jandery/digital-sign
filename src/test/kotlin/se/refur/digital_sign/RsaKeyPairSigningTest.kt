package se.refur.digital_sign

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class RsaKeyPairSigningTest {

    private val stringToSign = "This is a string to sign"

    private val firstKeyPair = RsaKeyPairSigning.generate()
    private val secondKeyPair = RsaKeyPairSigning.generate()

    @Test
    fun `validate signature, use same public key, true`() {
        val signature = firstKeyPair.createSignature(stringToSign)
        Assertions.assertTrue(firstKeyPair.validateSignature(stringToSign, signature))
        Assertions.assertTrue(RsaKeyPairSigning.validate(firstKeyPair.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, use different public key, false`() {
        val signature = secondKeyPair.createSignature(stringToSign)
        Assertions.assertFalse(firstKeyPair.validateSignature(stringToSign, signature))
        Assertions.assertFalse(RsaKeyPairSigning.validate(firstKeyPair.publicKey, stringToSign, signature))
    }
}