package uz.vianet.assymmetricencryption

import java.io.IOException
import java.security.GeneralSecurityException
import java.security.Key
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Arrays
import java.util.Base64
import javax.crypto.Cipher

class Asymmetric {

    var privateKey : PrivateKey
    var publicKey : PublicKey
    init {
        var keyGen = KeyPairGenerator.getInstance("RSA")
        keyGen.initialize(1024)
        val pair = keyGen.genKeyPair()
        privateKey = pair.private
        publicKey = pair.public
    }

    companion object{
        @Throws(Exception::class)
        fun encryptMessage(plainText:String,publicKey: String):String{
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE,loadPublicKey(publicKey))

            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.toByteArray()))
        }
        @Throws(GeneralSecurityException::class,IOException::class)
        private fun loadPublicKey(stored: String): Key {
            val data = Base64.getDecoder().decode(stored.toByteArray())
            val spec = X509EncodedKeySpec(data)
            val fact = KeyFactory.getInstance("RSA")

            return fact.generatePublic(spec)
        }
        @Throws(Exception::class)
        fun decryptMessage(encryptedText:String, privateKey: String):String{
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.DECRYPT_MODE,loadPrivateKey(privateKey))
            return String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)))
        }
        @Throws(GeneralSecurityException::class,IOException::class)
        private fun loadPrivateKey(key64: String): PrivateKey {
            val clear = Base64.getDecoder().decode(key64.toByteArray())
            val keySpek = PKCS8EncodedKeySpec(clear)
            val fact = KeyFactory.getInstance("RSA")
            val priv = fact.generatePrivate(keySpek)
            Arrays.fill(clear,0.toByte())
            return priv
        }
    }
}