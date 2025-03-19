package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    companion object {
        private const val DIESIDE = "side"
        private const val PREVIOUS_ROLL = "previous_roll"

        fun newInstance(side: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, side)
            }
        }
    }

    private lateinit var dieTextView: TextView
    private var dieSides: Int = 6
    private var currentRoll: Int = 1  // Store last roll value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(DIESIDE)?.let {
            dieSides = it
        }
        savedInstanceState?.getInt(PREVIOUS_ROLL)?.let {
            currentRoll = it  // Restore previous roll
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dieTextView.text = currentRoll.toString()  // Restore last roll
        view.setOnClickListener { throwDie() }
    }

    fun throwDie() {
        currentRoll = Random.nextInt(1, dieSides + 1)
        dieTextView.text = currentRoll.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PREVIOUS_ROLL, currentRoll)
    }
}