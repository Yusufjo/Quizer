package com.example.quizer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.quizer.databinding.ActivityMainBinding

class QuizMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    var currentQuestionListIndex = 0
    var score = 0
    val questionList = mutableListOf(
        Question("A slug's blood is green.", true),
        Question("You can lead a cow down stairs but not up stairs.", false),
        Question("Approximately one quarter of human bones are in the feet.", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        updateQuestion()
        setOnClickListeners()


    }

    private fun setOnClickListeners() {
        binding.buttonTrue.setOnClickListener {
            disableButtons()
            checkAnswer(true)
            currentQuestionListIndex++
            updateQuestion()
        }


        binding.buttonFalse.setOnClickListener {
            disableButtons()
            checkAnswer(false)
            currentQuestionListIndex++
            updateQuestion()

        }

        binding.buttonStartAgain.setOnClickListener {
            setStartAgainButton()
            updateQuestion()
        }
    }

    private fun checkAnswer(answer: Boolean) {
        if (questionList[currentQuestionListIndex].answer == answer) {
            if (answer) {
                binding.buttonTrue.setBackgroundColor(Color.GREEN)
            } else {
                binding.buttonFalse.setBackgroundColor(Color.GREEN)
            }
            score++
        } else {
            if (answer) {
                binding.buttonTrue.setBackgroundColor(Color.RED)
                binding.buttonFalse.setBackgroundColor(Color.GREEN)
            } else {
                binding.buttonTrue.setBackgroundColor(Color.GREEN)
                binding.buttonFalse.setBackgroundColor(Color.RED)
            }
        }
    }

    private fun updateQuestion() {
        if (questionList.size > currentQuestionListIndex) {
            handler.postDelayed({
                binding.run {
                    textViewQuestion.text = questionList[currentQuestionListIndex].question
                    textViewScoreSize.text = score.toString()
                    restartButtonColors()
                    enableButtons()
                }
            }, 1000)
        } else {
            handler.postDelayed({
                setFinalScreen()
                enableButtons()
            }, 1000)
        }
        setSeekBar()
    }

    private fun setFinalScreen() {
        binding.run {
            buttonTrue.visibility = View.GONE
            buttonFalse.visibility = View.GONE
            buttonStartAgain.visibility = View.VISIBLE
            binding.textViewQuestion.text =
                "Quiz is finished. YourScore: ${questionList.size}/${score}"
        }
    }

    private fun restartButtonColors() {
        binding.buttonTrue.setBackgroundColor(getColor(R.color.backround))
        binding.buttonFalse.setBackgroundColor(getColor(R.color.backround))
    }

    private fun enableButtons() {
        binding.buttonTrue.isEnabled = true
        binding.buttonFalse.isEnabled = true
    }

    private fun disableButtons() {
        binding.buttonTrue.isEnabled = false
        binding.buttonFalse.isEnabled = false
    }

    private fun setStartAgainButton() {
        score = 0
        currentQuestionListIndex = 0
        handler.postDelayed({
            binding.buttonStartAgain.visibility = View.GONE
            binding.buttonTrue.visibility = View.VISIBLE
            binding.buttonFalse.visibility = View.VISIBLE
        }, 1000)
    }

    private fun setSeekBar() {
        binding.seekBar.run {
            max = questionList.size
            progress = currentQuestionListIndex
            setOnTouchListener { view, motionEvent -> true }
        }
    }
}