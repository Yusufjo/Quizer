package com.example.quizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizer.databinding.ActivityMainBinding

class QuizMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val questionList = mutableListOf(
            QuizDataClass("A slug's blood is green.", true),
            QuizDataClass("You can lead a cow down stairs but not up stairs.", false),
            QuizDataClass("Approximately one quarter of human bones are in the feet.", true)
        )
        binding.textViewQuestion.text = questionList.first().question

    }
}