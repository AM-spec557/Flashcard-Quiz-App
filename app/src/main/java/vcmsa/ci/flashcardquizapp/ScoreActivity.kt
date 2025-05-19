package vcmsa.ci.flashcardquizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // These are the following that contain arrays
        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("answers") ?: BooleanArray(0)

        // This is the TextView and Buttons
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val feedbackText = findViewById<TextView>(R.id.finalFeedback)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)
        // Author: Openai ChatGPT
        // url: https://chat.openai.com

        // This gives the detail of the scoreText and feedbackText
        scoreText.text = "You scored: $score / 5"
        feedbackText.text = if (score >= 3) "Great job!" else "Keep practising!"

        // This gives the functions of the reviewButton
        reviewButton.setOnClickListener {
            try {
                val reviewText = findViewById<TextView>(R.id.reviewText)
                val reviewDetails = StringBuilder()
                for (i in questions.indices) {
                    reviewDetails.append("Q: ${questions[i]}\nA: ${if (answers[i]) "True" else "False"}\n\n")
                }
                reviewText.text = reviewDetails.toString()
            } catch (e: Exception) {
                Toast.makeText(this, "Show results review", Toast.LENGTH_SHORT).show()
            }
            // Author: Openai ChatGPT
            // url: https://chat.openai.com

            // This gives the functions of the exitButton
            exitButton.setOnClickListener {
                finishAffinity()  // Close the app
                // Author: Openai ChatGPT
                // url: https://chat.openai.com
            }
        }
    }
}