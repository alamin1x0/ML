package com.developeralamin.ml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl
import android.view.translation.Translator
import com.developeralamin.ml.databinding.ActivityTranslateBinding
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translation.getClient
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTranslateBinding
    var originalText: String = ""
    lateinit var englishtoBangla: com.google.mlkit.nl.translate.Translator

    //lateinit var pDialog:SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setUpProgressDialog()

        binding.btnTranslate.setOnClickListener {
            originalText = binding.etOriginalLanguage.text.toString()

            prepareTranslateModel();
        }
    }

//    private fun setUpProgressDialog() {
//        pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading");
//        pDialog.setCancelable(false);
//        pDialog.show();
//    }


    private fun prepareTranslateModel() {
        val options: TranslatorOptions = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.BENGALI)
            .build()

        englishtoBangla = Translation.getClient(options)

//        pDialog.titleText = "Model Downloading...."
//        pDialog.show()

        englishtoBangla.downloadModelIfNeeded(originalText).addOnSuccessListener {

           // pDialog.dismissWithAnimation()
            translateText()

        }.addOnFailureListener {
            //pDialog.dismissWithAnimation()
            binding.resultTraslate.text = "Error ${it.message}"
        }
    }

    private fun translateText() {
//        pDialog.titleText = "Translate Text"
//        pDialog.show()

        englishtoBangla.translate(originalText).addOnSuccessListener {
            //pDialog.dismissWithAnimation()
            binding.resultTraslate = it
        }.addOnFailureListener {
            //pDialog.dismissWithAnimation()
            binding.resultTraslate.text = "Error ${it.message}"
        }
    }
}