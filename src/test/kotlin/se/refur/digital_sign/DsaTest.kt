package se.refur.digital_sign

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DsaTest {

    private val stringToSign = "This is a string to sign"

    private val firstKeyPair = AlgorithmEnum.DSA.generate()
    private val secondKeyPair = AlgorithmEnum.DSA.generate()

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

    @Test
    fun `validate signature, read from file with matching key set, true `() {
        val dsaFirst = AlgorithmEnum.DSA.readFromFile(ResourceReader.DSA_FIRST.getFullPath())
        val signature = dsaFirst.createSignature(stringToSign)
        Assertions.assertTrue(DsaImpl.validateSignature(dsaFirst.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate signature, read from file with different key set, false`() {
        val dsaFirst = AlgorithmEnum.DSA.readFromFile(ResourceReader.DSA_FIRST.getFullPath())
        val dsaSecond = AlgorithmEnum.DSA.readFromFile(ResourceReader.DSA_SECOND.getFullPath())
        val signature = dsaFirst.createSignature(stringToSign)
        Assertions.assertFalse(DsaImpl.validateSignature(dsaSecond.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate base64 signature, read from file with matching key set, true `() {
        val dsaFirst = AlgorithmEnum.DSA.readFromFile(ResourceReader.DSA_FIRST.getFullPath())
        val signature = dsaFirst.createBase64Signature(stringToSign)
        Assertions.assertTrue(DsaImpl.validateBase64Signature(dsaFirst.publicKey, stringToSign, signature))
    }

    @Test
    fun `validate base64 signature, read from file with different key set, false`() {
        val dsaFirst = AlgorithmEnum.DSA.readFromFile(ResourceReader.DSA_FIRST.getFullPath())
        val dsaSecond = AlgorithmEnum.DSA.readFromFile(ResourceReader.DSA_SECOND.getFullPath())
        val signature = dsaFirst.createBase64Signature(stringToSign)
        Assertions.assertFalse(DsaImpl.validateBase64Signature(dsaSecond.publicKey, stringToSign, signature))
    }
}