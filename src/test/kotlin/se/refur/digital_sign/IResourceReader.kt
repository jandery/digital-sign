package se.refur.digital_sign

import java.nio.file.Files
import java.nio.file.Paths

/**
 * The purpose of this file is to read files from test resources
 */
interface IResourceReader {
    val resourcePath: String

    fun readFile(): String = String(Files.readAllBytes(Paths.get(getFullPath())))

    fun getFullPath(): String = "${System.getProperty("user.dir")}/src/test/resources/$resourcePath"
}

enum class ResourceReader(override val resourcePath: String) : IResourceReader {
    DSA_FIRST("/dsaFirst"),
    DSA_SECOND("dsaSecond"),
    RSA_FIRST("/rsaFirst"),
    RSA_SECOND("/rsaSecond");

}