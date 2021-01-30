package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //ustawiamy wygląd strony definiowany w xml

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //flaga ustawiona by aplikacja była na pełny ekran

        btn_start.setOnClickListener {
            if(et_name.text.toString().isEmpty()){
                Toast.makeText(this,"Wprowadź swoje imię",Toast.LENGTH_SHORT).show() //jeżeli ktoś nie wprowadzi swojego imienia wyświetla krótki komunikat by wprowadzić swoje imie
            }else{
                val intent = Intent(this, QuizQuestionsActivity::class.java) //ustalamy zmienną intent by odpalić następną aktywność
                intent.putExtra(Constants.USER_NAME, et_name.text.toString()) //przesyłamy do zmieniej user_name w constans nick wpisany w polu et_name
                startActivity(intent) //startujemy nową aktywność
                finish() //kończymy aktualaną aktywność
            }
        }

    }
}
