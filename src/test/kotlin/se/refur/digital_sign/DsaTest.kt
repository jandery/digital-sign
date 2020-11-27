package se.refur.digital_sign

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DsaTest {

    private val stringToSign = "This is a string to sign"

    private val firstKeyPair = DsaSigning.generate()
    private val secondKeyPair = DsaSigning.generate()

    @Test
    fun `validate signature, use same public key, true`() {
        val signature = firstKeyPair.createSignature(stringToSign)
        Assertions.assertTrue(DsaSigning.validateSignature(firstKeyPair.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, use different public key, false`() {
        val signature = secondKeyPair.createSignature(stringToSign)
        Assertions.assertFalse(DsaSigning.validateSignature(firstKeyPair.publicKey, stringToSign, signature))
    }
}