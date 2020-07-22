package com.example.quizapp

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        //1
        val que1 = Question(
            1,
            "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_argentina,
            "Argentyna",
            "Australia",
            "Armenia",
            "Austria",
            1)

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_australia,
            "Angola", "Austria",
            "Australia", "Armenia", 3
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_brazil,
            "Białoruś", "Maroko",
            "Meksyk", "Brazylia", 4
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_belgium,
            "Bahamy", "Belgia",
            "Białoruś", "Niemcy", 2
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_fiji,
            "Turcja", "Francja",
            "Fiji", "Finlandia", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_germany,
            "Niemcy", "Belgia",
            "Grecja", "Żadna z tych", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_denmark,
            "Dominikana", "Egipt",
            "Dania", "Etiopia", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_india,
            "Irlandia", "Iran",
            "Włochy", "Indie", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_new_zealand,
            "Australia", "Nowa Zelandia",
            "Węgry", "USA", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, "Do jakiego państwa należy ta flaga?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwejt", "Jordan",
            "Sudan", "Palestyna", 1
        )

        questionsList.add(que10)

        return questionsList
    }
}