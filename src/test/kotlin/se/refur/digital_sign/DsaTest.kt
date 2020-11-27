package se.refur.digital_sign

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DsaTest {

    private val stringToSign = "This is a string to sign"

    private val firstKeyPair = AlgorithmEnum.DSA.generate()
    private val secondKeyPair = AlgorithmEnum.RSA.generate()

    @Test
    fun `validate signature, use same public key, true`() {
        val signature = firstKeyPair.createSignature(stringToSign)
        Assertions.assertTrue(DsaImpl.validateSignature(firstKeyPair.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, use different public key, false`() {
        val signature = secondKeyPair.createSignature(stringToSign)
        Assertions.assertFalse(DsaImpl.validateSignature(firstKeyPair.publicKey, stringToSign, signature))
    }
}