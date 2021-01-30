package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result) //ustawiamy wygląd strony definiowany w xml

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //flaga ustawiona by aplikacja była na pełny ekran

        val username = intent.getStringExtra(Constants.USER_NAME) //pobieramy nick gracza i zapisujemy do username
        tv_name.text = username //ustawiamy nick pobrany w tv_name

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tv_score.text = "Twój wynik to $correctAnswers na $totalQuestions" //tv_score ustawiamy na prawidłowy

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java)) //kliknięcie przycisku finish powoduje wystartowanie mainactivity
            finish()
        }

    }
}
