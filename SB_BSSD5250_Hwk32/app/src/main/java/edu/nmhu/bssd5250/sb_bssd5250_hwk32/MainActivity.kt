package edu.nmhu.bssd5250.sb_bssd5250_hwk32

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit
import java.util.*

class MainActivity : AppCompatActivity() {

    private var fid = 0
    private var which: Boolean = false
    private var fragmentLinearLayout: LinearLayoutCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val addButton = Button(this).apply {
            text = "+"
            id = View.generateViewId()
            setOnClickListener {
                supportFragmentManager.commit {
                    //setReorderingAllowed(true)

                    // do not add more than 10 notes
                    if (supportFragmentManager.fragments.size >= 10) {
                        //TODO add Alert Dialog here
                        val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                        alert.setTitle("Notes limit Exceeded")
                        alert.setMessage("Notes limit of 10 reached")
                        alert.setPositiveButton("OK", null)
                        alert.show()
                    }
                    else {
                        which = false
                        add(fid, NoteFragment.newInstance(View.generateViewId(), which), null)
                    }
                }
            }
        }

        val addButtonRed = Button(this).apply {
            text = "+"
            id = View.generateViewId()
            setOnClickListener {
                supportFragmentManager.commit {
                    //setReorderingAllowed(true)

                    // do not add more than 10 notes
                    if (supportFragmentManager.fragments.size >= 10) {
                        //TODO add Alert Dialog here
                        val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                        alert.setTitle("Notes limit Exceeded")
                        alert.setMessage("Notes limit of 10 reached")
                        alert.setPositiveButton("OK", null)
                        alert.show()
                    }
                    else {
                        which = true
                        add(fid, NoteFragment.newInstance(View.generateViewId(), which), null)
                    }
                }
            }
        }

        val buttonsLinearLayout = LinearLayoutCompat(this).apply {
            id = View.generateViewId()
            fid = id
            orientation = LinearLayoutCompat.HORIZONTAL
            setBackgroundColor(Color.GRAY)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            fragmentLinearLayout?.let {
                (layoutParams as RelativeLayout.LayoutParams).addRule(
                    RelativeLayout.ABOVE, it.id)
            }
            addView(addButton)
            addView(addButtonRed)
        }

        fragmentLinearLayout = LinearLayoutCompat(this).apply {
        id = View.generateViewId()
        fid = id
        orientation = LinearLayoutCompat.VERTICAL
        setBackgroundColor(Color.BLUE)
            layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)
            (layoutParams as RelativeLayout.LayoutParams).addRule(
                RelativeLayout.BELOW, buttonsLinearLayout.id)
        }



        val relativeLayout = RelativeLayout(this).apply {
                setBackgroundColor(Color.GRAY)
                addView(buttonsLinearLayout)
                addView(fragmentLinearLayout)
            }

            setContentView(relativeLayout)
        }
}