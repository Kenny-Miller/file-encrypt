import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.Key
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec

fun main() {
    var run = true
    while (run) {
        print("S ")
        var line = readLine()

        when (line) {
            "p" -> print()
            "a" -> append()
            "d" -> decrypt()
            "e" -> encrypt()
            "q" -> {
                run = false
                println("Exiting program!")
            }
            else -> {
                println("Invalid Command!")
                run = false
            }
        }
    }
}

private fun decrypt() {
    try {
        print("File to decrypt: ")
        var filename = readLine()
        var inputFile = File(filename)
        var outputFile = File("decrypted.txt")
        fileProcessor(Cipher.DECRYPT_MODE, inputFile, outputFile)
    } catch (ex: Exception) {
        println("An error has occurred when trying to decrypt the file!")
    }
}

private fun encrypt() {
    try {
        print("File to encrypt: ")
        var filename = readLine()
        val inputFile = File("text.txt")
        val outputFile = File("encrypted.txt")
        fileProcessor(Cipher.ENCRYPT_MODE, inputFile, outputFile)
    } catch (e: Exception) {
        println("An error has occurred when trying to encrypt the file!")
    }
}

private fun print() {
    try {
        print("File to print: ")
        var filename = readLine()
        var file = File(filename)
        file.forEachLine { println(it) }
    } catch (e: Exception) {
        println("An error has occurred when trying to print the file!")
    }
}

private fun append() {
    try {
        print("File to append to: ")
        var filename = readLine()
        var file = File(filename)
        println("Text to write. Type :quit: to end.")
        var text = ""
        var writing = true
        while (writing) {
            var line = readLine()
            if (line == ":quit:") {
                writing = false;
            } else {
                text += "$line\n"
            }
        }
        file.appendText(text)
    } catch (ex: Exception) {
        println("An error has occurred when trying to append the file!")
    }
}

fun fileProcessor(cipherMode: Int, inputFile: File, outputFile: File) {
    val key = "This is a secret"
    val secretKey: Key = SecretKeySpec(key.toByteArray(), "AES")
    val cipher = Cipher.getInstance("AES")
    cipher.init(cipherMode, secretKey)
    val inputStream = FileInputStream(inputFile)
    val inputBytes = ByteArray(inputFile.length().toInt())
    inputStream.read(inputBytes)
    val outputBytes = cipher.doFinal(inputBytes)
    val outputStream = FileOutputStream(outputFile)
    outputStream.write(outputBytes)
    inputStream.close()
    outputStream.close()
}




