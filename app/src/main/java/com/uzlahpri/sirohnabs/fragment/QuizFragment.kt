package com.uzlahpri.sirohnabs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.uzlahpri.sirohnabs.R
import com.uzlahpri.sirohnabs.databinding.FragmentQuizBinding
import com.uzlahpri.sirohnabs.model.Quiz

class QuizFragment : Fragment() {

    private val questions: MutableList<Quiz> = mutableListOf(
        Quiz(
            text = "Siapakah nama ayah nabi Muhammad ?",
            answers = listOf("Abdullah", "Ali", "Abu Jahal", "Abu Muthallib")
        ),
        Quiz(
            text = "Tahun apakah Nabi Muhammad SAW lahir ?",
            answers = listOf("Gajah", "Ular", "Kambing", "Unta")
        ),
        Quiz(
            text = "Dakwah Nabi Muhammad SAW di Makkah selama berapa tahun ?",
            answers = listOf("13", "14", "12", "11")
        ),
        Quiz(
            text = "Siapakah nama sahabat yang terkenal di langit ?",
            answers = listOf(
                "Uwais Al Qarni",
                "Hamzah bin Abdul munthalib",
                "Khalid bin Walid",
                "Aburrahman bin Auf"
            )
        ),
        Quiz(
            text = "Siapa menantu Nabi Muhammad SAW yang juga menjadi khalifah ?",
            answers = listOf(
                "Ali bin Abi Tholib",
                "Abu Bakar Ash-Shiddiq",
                "Utsman bin Affan",
                "Umar bin Khattab"
            )
        ),
        Quiz(
            text = "Siapakah nama sahabat yang menemani Rasulullah pada waktu hijrah pertama kali ke Madinah ?",
            answers = listOf(
                "Ali bin Abi Tholib",
                "Abu Bakar As-Shiddiq",
                "Utsman bin Affan",
                "Umar bin Khattab"
            )
        ),
        Quiz(
            text = "Kapan Nabi Muhammad lahir ?",
            answers = listOf(
                "12 Rabiul Awal",
                "10 Rabiul Akhir",
                "12 Rabiul Akhir",
                "10 Rabiul Awal"
            )
        ),
        Quiz(
            text = "Siapa nama kakek Nabi Muhammad ?",
            answers = listOf(
                "Abdul Mutholib",
                "Abu Tholib",
                "Abu Jahal",
                "Abu Lahab"
            )
        ),
        Quiz(
            text = "Daerah manakah Abdurahman bin Auf berasal ?",
            answers = listOf("Bani Zuhrah", "Bani Israil", "Yaman", "Makkah")
        ),
        Quiz(
            text = "Siapa nama paman Nabi Muhammad ?",
            answers = listOf("Abu Tholib", "Abdul Mutholib", "Khalid bin Walid", "Uwais Al Qarni")
        )
    )

    private var questionIndex = 0
    lateinit var currentQuestion: Quiz
    lateinit var answers: MutableList<String>
    private val numberIndicatorQuestion = Math.min((questions.size + 1) /2, 5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val quizBinding =
            DataBindingUtil.inflate<FragmentQuizBinding>(
                inflater,
                R.layout.fragment_quiz,
                container,
                false
            )
        randomQuestion()
        quizBinding.quiz = this
        quizBinding.btnSubmit.setOnClickListener { view: View ->
            val checkedId = quizBinding.rgQuiz.checkedRadioButtonId
            if (-1 != checkedId) {
                var answerCorrectionPosition = 0
                when (checkedId) {
                    R.id.rb_option_2 -> answerCorrectionPosition = 1
                    R.id.rb_option_3 -> answerCorrectionPosition = 2
                    R.id.rb_option_4 -> answerCorrectionPosition = 3
                }
                if (answers[answerCorrectionPosition] == currentQuestion.answers[0]) {
                    questionIndex++
                    if (questionIndex < numberIndicatorQuestion) {
                        currentQuestion = questions[questionIndex]
                        setNumberQuestion()
                        quizBinding.invalidateAll()
                    } else {
                        view.findNavController().navigate(R.id.action_quizFragment_to_wonFragment)
                    }
                } else {
                    view.findNavController().navigate(R.id.action_quizFragment_to_overFragment)
                }
            }
        }
        return quizBinding.root
    }

    private fun randomQuestion() {
        questions.shuffle()
        questionIndex = 0
        setNumberQuestion()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setNumberQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_siroh_nabs, questionIndex + 1, numberIndicatorQuestion)
    }


}