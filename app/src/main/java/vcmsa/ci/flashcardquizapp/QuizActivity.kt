package vcmsa.ci.flashcardquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    // These are the five questions
    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994",
        "The Earth is flat",
        "Water freezes at 0 degrees Celsius",
        "Java and Kotlin are the same language",
        "Android is developed by Google"
    )
    // Author: Openai ChatGPT
    // url: https://chat.openai.com

    // This is the answers containing True and false
    private val answers = arrayOf(true, false, true, false, true)

    private var currentQuestion = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // The following buttons and texts
        val questionText = findViewById<TextView>(R.id.questionText)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        // Author: Openai ChatGPT
        // url: https://chat.openai.com

        fun loadQuestion() {
            questionText.text = questions[currentQuestion]
            feedbackText.text = ""
        }
        loadQuestion()

        // This has the user answers of correct and incorrect
        val answerListener = { userAnswer: Boolean ->
            if (userAnswer == answers[currentQuestion]) {
                feedbackText.text = "Correct!"
                score++
            } else {
                feedbackText.text = "Incorrect"
            }
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }
        // Author: Openai ChatGPT
        // url: https://chat.openai.com

        // The true and false button
        trueButton.setOnClickListener { answerListener(true) }
        falseButton.setOnClickListener { answerListener(false) }

        // The nextButton and functions
        nextButton.setOnClickListener {
            currentQuestion++
            if (currentQuestion < questions.size) {
                loadQuestion()
                trueButton.isEnabled = true
                falseButton.isEnabled = true
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                startActivity(intent)
                finish()
                // Author: Openai ChatGPT
                // url: https://chat.openai.com
            }
        }
    }
}