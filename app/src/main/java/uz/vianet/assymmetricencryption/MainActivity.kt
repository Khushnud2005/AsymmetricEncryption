package uz.vianet.assymmetricencryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import uz.vianet.assymmetricencryption.Asymmetric.Companion.decryptMessage
import uz.vianet.assymmetricencryption.Asymmetric.Companion.encryptMessage
import java.security.PrivateKey
import java.util.Base64

class MainActivity : AppCompatActivity() {
    private lateinit var privatek: PrivateKey
    lateinit var et_encrypt: EditText
    lateinit var et_private: EditText
    lateinit var et_result: EditText
    lateinit var decrypt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testAsymmetric()
        initViews()
    }

    private fun initViews() {
        et_encrypt = findViewById(R.id.et_input)
        et_encrypt = findViewById(R.id.et_input)
        et_private = findViewById(R.id.et_privateK)
        et_result = findViewById(R.id.et_result)
        decrypt = findViewById(R.id.decrypt)
        decrypt.setOnClickListener {
            decrytpStringByRsa()
        }
    }

    private fun decrytpStringByRsa() {
        val encrStr = et_encrypt.text.toString().trim()
        val prvKey = et_private.text.toString().trim()
        val result = decryptMessage(encrStr,prvKey)

        et_result.setText(result)
    }

    private fun testAsymmetric() {
        val secretText = "Boymurotov Hushnud"
        val keyPairGenerator = Asymmetric()
        val privateKey = Base64.getEncoder().encodeToString(keyPairGenerator.privateKey.encoded)
        val publicKey = Base64.getEncoder().encodeToString(keyPairGenerator.publicKey.encoded)

        //Log.d("###","PublicKey - $publicKey")
       // Log.d("###","PrivateKey - $privateKey")

       // Log.d("###","SecretText - $secretText")
        val encryptedText = encryptMessage(secretText,publicKey)
        //Log.d("###","EncryptedText - $encryptedText")
        val decryptedText = decryptMessage(encryptedText,privateKey)
        //Log.d("###","DecryptedText - $decryptedText")


        val encrStr = "JD9yPcl4tgrQULf1HfKcFW4zT4w0Isb/Dg38h+7gCZNFrSmDxlNAePHQDFqzbax9zMwD1dqiA6rxnWdO1I3NLwjYMbbocYiZkLMH5xbKt4jODyTmJylUNBBacPrLzV8yEBff7C+Cm/veMJEYBe8Qafzr0zeWxv1W4DTJ1yDKnWc"

        val prvKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKs164xhodihpp2c8K0zGNAZqLFP3yqEw1VkXeilyz0TdGWFyE+WvhRfGNwbJMxA3h5P+UyVFzMAI5PDcJjyUTuWBKzGFkakVdcoMdNxW29XYmai78vaSfJ9jsWyakuV7HHJP/2clJNITCmhH2Pv/oUw5kNsEojRIr/PkAhymPx/AgMBAAECgYAPwt56JWYXVxugsy5q22MuNnZ+SuU3TlKHHWlnE0Ay/AQBRpVqp5dnBlwiWHNaREx5C5Feg0kdSPwoIbFPZdtOvda23C8WiGO9caQ92fNJo+z1jwCWFAM02UHg3rsoE/gHFmErwnvpLzmR4aB9O7XLOs0lcfTxhcs2RORbpuZFmQJBAPIaiwx2V3PuV0rMXeqmwDIQviw2/dXZD55MVJ5knxGKQ4ZI8b+S2zStZNdFjCv/7IJsz4GsGLV9VDiTbViQNCkCQQC1Ca278lI70GRCvNAeID7nXvYREANzBzcGCjiIZB8p0mPZptYv6zytpSEHDPVPpP8Z/t6/jGbBe2yIdJZ+dABnAkApakb9E8V0DIKcRgeO24WnStvtXl7f1Sjp0G7tRZM8geV5NEM+nVWoVj0Y4knPQRFpYknBCfqUGWNt5jJfYxO5AkA8W5+6282i95WV0pGomaOfTD/J7IbZ4PvtXZb/kjH5pZV4yXD7xPgUBMwAJ8fZ5+7NYfYt8seHlnjW/df3DQHdAkEA4UGfrC/e9n8eeSzkFAy3wAedLSFypqkr134KeAUVknxAPc6u89JmIM4fkIH0BtMyqZWGxd3zgxYarlFl05ltjg"
        val result = decryptMessage(encrStr,prvKey)
        Log.d("###","DecryptedText - $result")
    }
}