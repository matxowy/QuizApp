package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlin.random.Random

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1 //podstawowa i pierwsza pozycja pytania
    private var mQuestionsList: ArrayList<Question>? = null //zmienna na pytania
    private var mSelectedOptionPosition: Int = 0 //zmienna która zapisuje wybraną opcję
    private var mCorrectAnswers: Int = 0 //zmienna w której jest zapisana ilość poprawnych odpowiedzi
    private var mUserName: String? = null //zmienna z nazwa gracza
    private var numOfQue: Int = 0 //zmienna określająca które pytanie teraz będzie wybrane z listy
    private var randomQuestionList: ArrayList<Int> = ArrayList() //zmienna na losowa kolejnosc pytan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions) //ustawienie wyglądu z xml

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN //ustawienie flagi żeby aplikacja była na fullscrenie

        mUserName = intent.getStringExtra(Constants.USER_NAME) //ustawienie pod zmienna mUserName wprowadzone imie we wczesniejszym intencie

        mQuestionsList = Constants.getQuestions() //dodanie bazy pytan do zmiennej

        setQuestion() //funkcja ustawiajaca pytanie

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)

        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion() {

        //generowanie kolejnosci pytan
        while(randomQuestionList.size != 10){ //generuje dopoki nie bedzie 10 wygenerowanych liczb w liscie bo tyle pytan jest w quizie
            var num = Random.nextInt(0,10) //generowanie liczby z zakresu 0 do 9
            if(!randomQuestionList.contains(num)) {
                randomQuestionList.add(num)
            }
        }

        val question = mQuestionsList!![randomQuestionList[numOfQue]]

        defaultOptionsView()

        /*if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "ZAKOŃCZ"
        }else{
            btn_submit.text = "ZATWIERDŹ"
        }*/
        btn_submit.text = "ZATWIERDŹ"

        progressBar.progress = mCurrentPosition //ustawia progres w progresbar na aktualny z pozycji pytania
        tv_progress.text = "$mCurrentPosition / ${progressBar.max}" //pod paskiem progresu napis ktore pytanie aktualnie jest na 10(bo max progres bar jest tak ustawiony)

        tv_question.text = question!!.question //ustawianie pytania

        iv_image.setImageResource(question.image) //ustawianie obrazka

        //ustawianie poszczegolnych opcji odpowiedzi
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour

    }

    //funkcja ustawiajaca domyslny widok odpowiedzi
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        //dodawanie do listy opcji odpowiedzi
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        //ustawienie domyslnego wygladu odpowiedzi
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)

        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4)
            }

            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) { //sprawdza czy po kliknieciu przycisku jest wybrana jakaś odpowiedź, jeżeli nie to:
                    if(mCurrentPosition != numOfQue){ //jeżeli ktoś nie zaznaczy odpowiedzi to żeby zmieniło się pytanie trzeba zastosować tego ifa
                        numOfQue++ //następne pytanie z listy
                    }
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> { //sprawdzenie czy aktualne pytanie nie jest już ostatnim pytaniem, jeżeli jest mniejsze lub równe czyli nie to wywołuje się setQuestion by ustawić nastepne pytanie
                            setQuestion()
                        }
                        else -> { //jeżeli mCurrentPosition przekracza już listę pytań czyli jest poza 10pytaniem to wtedy przekazywane są dane do końcowego intentu ResultActivity i jest on odpalany
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else { //jeżeli po kliknięciu przycisku jest wybrana jakaś opcja to:
                    val question = mQuestionsList?.get(randomQuestionList[numOfQue]) //zapisujemy do zmiennej question aktualne pytanie
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg) //jeżeli nie zgadza się wcześniej wypisana w constans pozycja poprawnej odpowiedzi z wybraną ustawia wygląd odpowiedzi na złą czyli czerwona ramka
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg) //dobra odpowiedź jest oznaczana zieloną ramką

                    if (mCurrentPosition == mQuestionsList!!.size) { //jeżeli aktualna pozycja jest równa z wielkością listy to znaczy, że to jest ostatnie pytanie i wtedy ustawia napis w przycisku na zakończ w przeciwnym wypadku następne pytanie
                        btn_submit.text = "ZAKOŃCZ"
                    } else {
                        btn_submit.text = "Następne pytanie"
                    }
                    mSelectedOptionPosition = 0 //zresetowanie wybranej opcji potrzebne do następnych pytań
                    numOfQue++ //następne pytanie z listy

                }

            }


        }

    }

    private fun answerView(answer: Int, drawableView: Int) { //funkcja potrzebna do ustawiania wyglądu odpowiedzi(dobra lub zła)
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    //funkcja do ustawiania stylu wybranej odpowiedzi
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }
}
