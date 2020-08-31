package nl.soffware.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nl.soffware.madlevel1task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun updateUI(){
        val imageName = String.format("dice%1", currentThrow)
        val imageID = resources.getIdentifier(imageName, "drawable", packageName)
        binding.imgDiceView.setImageDrawable(getDrawable(imageID))
        binding.etLastThrow.text = getString(R.string.last_throw, lastThrow)
    }
}