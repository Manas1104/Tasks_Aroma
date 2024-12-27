import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val story1Button = findViewById<Button>(R.id.story1_button)
        val story2Button = findViewById<Button>(R.id.story2_button)
        val story3Button = findViewById<Button>(R.id.story3_button)

        // Start StoryActivity with the selected story
        story1Button.setOnClickListener { openStory(1) }
        story2Button.setOnClickListener { openStory(2) }
        story3Button.setOnClickListener { openStory(3) }
    }

    private fun openStory(storyId: Int) {
        val intent = Intent(this, StoryActivity::class.java)
        intent.putExtra("STORY_ID", storyId)
        startActivity(intent)
    }
}
