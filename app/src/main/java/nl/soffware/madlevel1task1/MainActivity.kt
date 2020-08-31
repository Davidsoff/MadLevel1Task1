package nl.soffware.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import nl.soffware.madlevel1task1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    private val LOWER = 0
    private val EQUAL = 1
    private val HIGHER = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUI()
        binding.btnLower.setOnClickListener {
            handleClick(LOWER)
        }
        binding.btnEquals.setOnClickListener {
            handleClick(EQUAL)
        }
        binding.btnHigher.setOnClickListener {
            handleClick(HIGHER)
        }
    }

    private fun handleClick(action: Int) {
        rollDice()
        animateDice(action)
    }

    private fun showMessage(action: Int) {
        when {
            action == LOWER && lastThrow > currentThrow -> showSuccess()
            action == EQUAL && lastThrow == currentThrow -> showSuccess()
            action == HIGHER && lastThrow < currentThrow -> showSuccess()
            else -> showFailure()
        }
    }

    private fun showSuccess() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
    }

    private fun showFailure() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
    }

    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
    }

    private fun animateDice(action: Int) {
        updateUI()
        val timer = object: CountDownTimer(1000,30) {
            override fun onTick(milisUntillFinished: Long) {
                setDiceImageTo((1..6).random())
            }
            override fun onFinish() {
                updateUI()
                showMessage(action)
            }
        }
        timer.start()
    }

    private fun setDiceImageTo(number: Int){
        val imageName = "dice$number"
        val imageID = resources.getIdentifier(imageName, "drawable", packageName)
        binding.imgDiceView.setImageDrawable(getDrawable(imageID))
    }

    private fun updateUI(){
        setDiceImageTo(currentThrow)
        binding.etLastThrow.text = getString(R.string.last_throw, lastThrow)
    }
}