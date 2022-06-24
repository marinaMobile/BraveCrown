package air.com.hypah.io.slithe.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import air.com.hypah.io.slithe.R
import android.animation.ObjectAnimator
import android.os.Handler
import kotlinx.android.synthetic.main.fragment_second.*
import java.text.ChoiceFormat.nextDouble
import java.util.*
import kotlin.random.Random.Default.nextDouble


class Second : Fragment() {
    val random = Random()
    fun getRandom(min: Float, max: Float): Float {
        require(min < max) { "Invalid range [$min, $max]" }
        return min + random.nextFloat() * (max - min)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    override fun onStart() {
        super.onStart()
        crownIV.setOnClickListener{
            translater()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val updateHandler = Handler()

        val runnable = Runnable {
            updateDisplay()
        }

        updateHandler.postDelayed(runnable, 5000)


    }
    private fun translater() {
        val min = 0.0f
        val max = 1.0f
        val randomTwo = getRandom(min, max)
        val animation = crownIV.animate()
            .x(randomTwo * ll_wasp.width)
            .y(randomTwo * ll_wasp.height)

        animation.duration = 300
        animation.start()

    }
    private fun updateDisplay() {
        cardView.visibility = View.INVISIBLE
    }
}
