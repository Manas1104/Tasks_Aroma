import android.media.MediaPlayer
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.btn_play)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)
        val songTitle = findViewById<TextView>(R.id.song_title)

        // Load a local MP3 file
        val filePath = "/storage/emulated/0/Music/sample.mp3" // Replace with your file path
        val file = File(filePath)

        if (file.exists()) {
            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(filePath)
            mediaPlayer.prepare()

            songTitle.text = "Sample Song"

            // Play button logic
            playButton.setOnClickListener {
                if (isPlaying) {
                    mediaPlayer.pause()
                    playButton.text = "Play"
                } else {
                    mediaPlayer.start()
                    playButton.text = "Pause"
                }
                isPlaying = !isPlaying
            }

            // Update SeekBar
            seekBar.max = mediaPlayer.duration
            mediaPlayer.setOnPreparedListener {
                seekBar.max = mediaPlayer.duration
            }
            mediaPlayer.setOnCompletionListener {
                playButton.text = "Play"
                isPlaying = false
            }

            // SeekBar change listener
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })

        } else {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
