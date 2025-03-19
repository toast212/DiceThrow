package edu.temple.dicethrow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var die1Fragment: DieFragment
    private lateinit var die2Fragment: DieFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Only create new fragments if they don't already exist (first time or after process kill)
        if (savedInstanceState == null) {
            // Create first die with 6 sides using the factory method
            die1Fragment = DieFragment.newInstance(6)

            // Create second die with 6 sides using the factory method
            die2Fragment = DieFragment.newInstance(6)

            // Add fragments to their containers
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView1, die1Fragment)
                .replace(R.id.fragmentContainerView2, die2Fragment)
                .commit()
        } else {
            // If fragments already exist, just find references to them
            die1Fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView1) as DieFragment
            die2Fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as DieFragment
        }

        // Set up the Roll button
        findViewById<android.widget.Button>(R.id.rollDiceButton).setOnClickListener {
            die1Fragment.throwDie()
            die2Fragment.throwDie()
        }
    }
}
