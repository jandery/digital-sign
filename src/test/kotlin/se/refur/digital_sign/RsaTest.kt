package se.refur.digital_sign

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class RsaTest {

    private val stringToSign = "This is a string to sign"

    private val firstKeyPair = AlgorithmEnum.RSA.generate()
    private val secondKeyPair = AlgorithmEnum.RSA.generate()

    @Test
    fun `validate signature, use same public key, true`() {
        val signature = firstKeyPair.createSignature(stringToSign)
        Assertions.assertTrue(RsaImpl.validateSignature(firstKeyPair.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, use different public key, false`() {
        val signature = secondKeyPair.createSignature(stringToSign)
        Assertions.assertFalse(RsaImpl.validateSignature(firstKeyPair.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, read from file with matching key set, true `() {
        val rsaFirst = AlgorithmEnum.RSA.readFromFile(ResourceReader.RSA_FIRST.getFullPath())
        val signature = rsaFirst.createSignature(stringToSign)
        Assertions.assertTrue(RsaImpl.validateSignature(rsaFirst.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, read from file with different key set, false`() {
        val rsaFirst = AlgorithmEnum.RSA.readFromFile(ResourceReader.RSA_FIRST.getFullPath())
        val rsaSecond = AlgorithmEnum.RSA.readFromFile(ResourceReader.RSA_SECOND.getFullPath())
        val signature = rsaFirst.createSignature(stringToSign)
        Assertions.assertFalse(RsaImpl.validateSignature(rsaSecond.publicKey, stringToSign, signature))
    }
}
