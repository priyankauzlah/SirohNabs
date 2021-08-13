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
            text = "Siapakah nama nabi dan manusia pertama ?",
            answers = listOf("Nabi Adam", "Nabi Nuh", "Nabi Hud", "Nabi Yahya")
        ),
        Quiz(
            text = "Berapakah tinggi Nabi Adam ?",
            answers = listOf("60 Hasta", "50 Hasta", "70 Hasta", "80 Hasta")
        ),
        Quiz(
            text = "Siapakah pasangan Nabi Adam ?",
            answers = listOf("Hawa", "Aisyah", "Khadijah", "Annisa")
        ),
        Quiz(
            text = "Buah terlarang apa yang dimakan Adam dan Hawa di surga ?",
            answers = listOf(
                "Khuldi",
                "Kholid",
                "Khalid",
                "Khawailid"
            )
        ),
        Quiz(
            text = "Siapa nabi yang terkenal dengan kecerdasannya?",
            answers = listOf(
                "Nabi Idris",
                "Nabi Nuh",
                "Nabi Ayyub",
                "Nabi Hud"
            )
        ),
        Quiz(
            text = "Siapakah nama nabi yang menjadi manusia pertama yang bisa baca dan menulis ?",
            answers = listOf(
                "Nabi Idris",
                "Nabi Nuh",
                "Nabi Ayyub",
                "Nabi Hud"
            )
        ),
        Quiz(
            text = "Siapakah nama nabi yang pernah merasakan sakaratul maut dan dihidupkan kembali atas izin Allah. ?",
            answers = listOf(
                "Nabi Idris",
                "Nabi Nuh",
                "Nabi Musa",
                "Nabi Isa"
            )
        ),
        Quiz(
            text = "Nabi Nuh termasuk golongan ?",
            answers = listOf(
                "Ulul Azmi",
                "Ulul Albab",
                "Azmiul Albab",
                "Babul fashli"
            )
        ),
        Quiz(
            text = "Nabi Nuh adalah yang kaumnya mendapatkan adzab",
            answers = listOf(
                "Banjir besar",
                "Badai dengan awan yang penuh petir",
                "Hujan belalang",
                "Muntah darah"
            )
        ),
        Quiz(
            text = "Apa nama kaum Nabi Hud ?",
            answers = listOf("Ad", "Tsamud", "Hawariyyin", "Sodom")
        ),
        Quiz(
            text = "Nabi Salih adalah yang kaumnya mendapatkan adzab ...",
            answers = listOf(
                "Badai dengan awan yang penuh petir",
                "Banjir besar",
                "Hujan belalang",
                "Muntah darah"
            )
        ),
        Quiz(
            text = "Apa nama kaum Nabi Saleh ?",
            answers = listOf("Tsamud", "Ad", "Hawariyyin", "Sodom")
        ),
        Quiz(
            text = "Apa salah satu mukjizat Nabi Salih ?",
            answers = listOf(
                "Memunculkan unta betina dari batu besar",
                "Menghidupkan orang yang sudah mati",
                "Membelah lautan",
                "Mampu menurunkan ujan"
            )
        ),
        Quiz(
            text = "Siapakah nabi penutup ?",
            answers = listOf(
                "Nabi Muhammad",
                "Nabi Ibrahim",
                "Nabi Ilyasa",
                "Nabi Yunus"
            )
        ),
        Quiz(
            text = " Masjid apakah yang pertama kali dibangun oleh Nabi Muhammad ?",
            answers = listOf(
                "Masjid Kuba",
                "Masjid Nabawi",
                "Masjid Aqsa",
                "Masjid Haram"
            )
        ),
        Quiz(
            text = "Dimanakah Nabi Muhammad Saw bersembunyi ketika dikejar oleh kafir Quraisy ?",
            answers = listOf(
                "Gua Tsur",
                "Abwa",
                "Gua Hira",
                "Jabal rahman"
            )
        ),
        Quiz(
            text = " Tahun dimana Siti Khadijah isteri tercinta Rasul dan kakeknya Abdul Muthallib wafat disebut ?",
            answers = listOf(
                "Aamul Huzni",
                "Aamul Jamil",
                "Aamul Jamal",
                "Aamul Fitri"
            )
        ),
        Quiz(
            text = "Tahun kelahiran Nabi Muhammad Saw disebut dengan Tahun Gajah karena apa ?",
            answers = listOf(
                "Ada pasukan gajah yang hendak menghancurkan ka'bah",
                "ada perang gajah",
                "Diadakannya festival gajah",
                "Ada pasukan gajah yang hendak menyerang penduduk Mekkah"
            )
        ),
        Quiz(
            text = "Pada tanggal berapa Rasulullah dilahirkan ?",
            answers = listOf(
                "12 Rabiul Awal",
                "13 Rabiul Awal",
                "12 Rabiul Akhir",
                "13 Rabiul Akhir"
            )
        ),
        Quiz(
            text = "Peristiwa pasukan gajah diceritakan dalam Al-Qur'an Surah apa ?",
            answers = listOf(
                "Al-Fil",
                "Al-Kautsar",
                "Al-Humazah",
                "Al-Maun"
            )
        )
    )

    private var questionIndex = 0
    lateinit var currentQuestion: Quiz
    lateinit var answers: MutableList<String>
    private val numberIndicatorQuestion = Math.min((questions.size + 1) / 2, 5)

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