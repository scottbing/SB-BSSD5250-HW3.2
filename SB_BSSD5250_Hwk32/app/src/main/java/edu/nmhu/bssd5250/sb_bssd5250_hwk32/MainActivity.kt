package edu.nmhu.bssd5250.sb_bssd5250_hwk32

import android.R
import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit
import android.content.DialogInterface

class MainActivity : AppCompatActivity() {

    private var notes = 0  // number of notes
    private var fid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val addButton = Button(this).apply {
            text = "+"
            id = View.generateViewId()
            setOnClickListener {
                supportFragmentManager.commit {
                    //setReorderingAllowed(true)

                    // do not add more than 10 notes
                    if (notes > 3) {
                        //TODO add Alert Dialog here
                        val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                        alert.setTitle("Notes limit Exceeded")
                        alert.setMessage("Notes limit of 10 reached")
                        alert.setPositiveButton("OK", null)
                        alert.show()
                    }
                    else {
                        add(fid, NoteFragment.newInstance(), null)
                        notes+=1
                    }
                }
            }
        }

        val fragmentLinearLayout = LinearLayoutCompat(this).apply {
        id = View.generateViewId()
        fid = id
        orientation = LinearLayoutCompat.VERTICAL
        setBackgroundColor(Color.BLUE)
        layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)
        (layoutParams as RelativeLayout.LayoutParams).addRule(
            RelativeLayout.BELOW, addButton.id)
        }

        val relativeLayout = RelativeLayout(this).apply {
            setBackgroundColor(Color.GRAY)
            addView(addButton)
            addView(fragmentLinearLayout)
        }

        setContentView(relativeLayout)
    }
}