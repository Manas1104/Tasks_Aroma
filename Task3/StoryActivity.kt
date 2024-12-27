import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StoryActivity : AppCompatActivity() {

    private val storyContent = mapOf(
        1 to listOf(
            Pair("The brave lion lived in the jungle...", R.drawable.lion_image),
            Pair("One day, the lion helped a mouse...", R.drawable.mouse_image)
        ),
        2 to listOf(
            Pair("The clever rabbit tricked the fox...", R.drawable.rabbit_image),
            Pair("The fox never bothered the rabbit again...", R.drawable.fox_image)
        ),
        3 to listOf(
            Pair("The magical forest was full of wonders...", R.drawable.forest_image),
            Pair("The kids loved exploring it...", R.drawable.kids_image)
        )
    )

    private var currentPage = 0
    private var currentStoryId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        currentStoryId = intent.getIntExtra("STORY_ID", 1)

        val storyImage = findViewById<ImageView>(R.id.story_image)
        val storyText = findViewById<TextView>(R.id.story_text)
        val prevButton = findViewById<Button>(R.id.prev_page_button)
        val nextButton = findViewById<Button>(R.id.next_page_button)

        fun updatePage() {
            val (text, imageRes) = storyContent[currentStoryId]?.get(currentPage) ?: return
            storyText.text = text
            storyImage.setImageResource(imageRes)
        }

        prevButton.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                updatePage()
            }
        }

        nextButton.setOnClickListener {
            if (currentPage < (storyContent[currentStoryId]?.size ?: 1) - 1) {
                currentPage++
                updatePage()
            }
        }

        updatePage()
    }
}
