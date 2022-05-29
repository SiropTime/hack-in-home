package com.maltsev.stankinhack.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class SpeechClass(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context, this)
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            val result = tts!!.setLanguage(Locale(Locale.getDefault().language))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
                tts!!.language = Locale.US
            }
        }
    }

    fun speakOut(message: String) {
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

}