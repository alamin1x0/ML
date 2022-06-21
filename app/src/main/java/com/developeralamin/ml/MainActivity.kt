package com.developeralamin.ml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.developeralamin.ml.databinding.ActivityMainBinding
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentifier

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkButton.setOnClickListener {
            val langText:String = binding.etLangText.text.toString()
            if (langText.equals("")){
                Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
            }else{
                datectLang(langText)
            }
        }
    }

    private fun datectLang(langText: String) {

        val languageIdentifier : LanguageIdentifier = LanguageIdentification.getClient()


        languageIdentifier.identifyLanguage(langText)
            .addOnSuccessListener { languageCode ->
                if (languageCode == "und"){
                    binding.result.text="Can't identify language"
                    Toast.makeText(this, "Can't identify language.", Toast.LENGTH_SHORT).show()
                }else{
                    binding.result.text="Language: $languageCode"
                    Toast.makeText(this, "Language", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                binding.result.text = "Exception ${it.message}"
            }
    }
}