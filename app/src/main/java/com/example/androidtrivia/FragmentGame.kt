package com.example.androidtrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.androidtrivia.databinding.FragmentGameBinding

class FragmentGame : Fragment() {

    data class Question(
        val text: String,
        val answer: List<String>
    )

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (Or better yet, don't define the questions in code...)
    private val question: MutableList<Question> =
        mutableListOf(

            // Question One
            Question(
                text = "What is Android jetpack?",
                answer = listOf("All of these", "Documentation", "Tools", "Libraries")
            ),

            // Question Two
            Question(
                text = "What is the base class of layout?",
                answer = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
            ),
            // Question Three
            Question(
                text = "What layout do you use for complex screens?",
                answer = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
            ),

            // Question Four
            Question(
                text = "What do you use to push structured data into a layout?",
                answer = listOf("Data binding", "Data pushing", "Set text", "An OnClick method")
            ),

            // Question Five
            Question(
                text = "What method do you use to inflate layouts in fragments?",
                answer = listOf(
                    "onCreateView()",
                    "onActivityCreated()",
                    "onCreateLayout()",
                    "onInflateLayout()"
                )
            ),

            // Question Six
            Question(
                text = "What's the build system for Android?",
                answer = listOf("Gradle", "Graddle", "Grodle", "Groyle")
            ),

            // Question Seven
            Question(
                text = "Which class do you use to create a vector drawable?",
                answer = listOf(
                    "VectorDrawable",
                    "AndroidVectorDrawable",
                    "DrawableVector",
                    "AndroidVector"
                )
            ),

            // Question Eight
            Question(
                text = "Which one of these is an Android navigation component?",
                answer = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
            ),

            // Question Nine
            Question(
                text = "Which XML element lets you register an activity with the launcher activity?",
                answer = listOf(
                    "intent-filter",
                    "app-registry",
                    "launcher-registry",
                    "app-launcher"
                )
            ),

            // Question Ten
            Question(
                text = "What do you use to mark a layout for data binding?",
                answer = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
            )
        )


    lateinit var currentQuestion: Question
    lateinit var answer: MutableList<String>
    private var questionIndex = 0
    private val numQuestion = Math.min((question.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater,
            R.layout.fragment_game, container, false
        )

        // Shuffles the questions and sets the question index to the first question.
        randomQuestion()

        // bind this fragment class to the layout
        binding.game = this

        // add listener for submit button
        binding.submitBtn.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // do nothing if nothing answer was checked
            if (-1 != checkedId) {
                var correctAnswerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerId -> correctAnswerIndex = 1
                    R.id.thirdAnswerId -> correctAnswerIndex = 2
                    R.id.fourthAnswerId -> correctAnswerIndex = 3
                }

                // the first answer of in the index is the correct one.
                if (answer[correctAnswerIndex] == currentQuestion.answer[0]) {
                    questionIndex++

                    if (questionIndex < numQuestion) {
                        currentQuestion = question[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // we've won
                        view?.findNavController()
                            ?.navigate(
                                FragmentGameDirections
                                    .actionFragmentGameToGameWonFragment(numQuestion, questionIndex)
                            )
                    }

                } else {
                    // game over
                    view?.findNavController()
                        ?.navigate(FragmentGameDirections
                                .actionFragmentGameToFragmentGameOver()
                        )
                }
            }
        }


        return binding.root


    }


    // randomize question
    private fun randomQuestion() {
        question.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = question[questionIndex]
        // randomize the answer into a copy of the array
        answer = currentQuestion.answer.toMutableList()
        // and shuffle them
        answer.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(
            R.string.title_android_trivia_question, questionIndex + 1, numQuestion
        )
    }

}