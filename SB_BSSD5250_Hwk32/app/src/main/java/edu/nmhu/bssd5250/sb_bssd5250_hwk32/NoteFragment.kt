package edu.nmhu.bssd5250.sb_bssd5250_hwk32

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        setFragmentResultListener("noteDataChange") { requestLey, bundle ->
            val result = bundle.getString("nameKey")
            Log.d("NoteFragment",result.toString())
        }
    }

    val buttonWidth:Int = 200
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val nameView = TextView(context).apply {
            setText(R.string.name_place_holder)
        }
        val dateView = TextView(context).apply {
            setText(R.string.date_place_holder)
        }
        val descView = TextView(context).apply {
            setText(R.string.desc_place_holder)
        }

        val textHolderLL = LinearLayoutCompat(requireContext()).apply {
            orientation = LinearLayoutCompat.VERTICAL
            addView(nameView)
            addView(dateView)
            addView(descView)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            (layoutParams as RelativeLayout.LayoutParams).addRule(
                RelativeLayout.ALIGN_PARENT_LEFT)
        }
        //End Text for the left side

        //Edit button on hte right side
        val deleteButton = Button(requireContext()).apply {
            id = View.generateViewId()
            text = "Delete"
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            (layoutParams as RelativeLayout.LayoutParams).addRule(
                RelativeLayout.ALIGN_PARENT_RIGHT)

            setOnClickListener {
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Delete Note?")
                    setPositiveButton("Yes", DialogInterface.OnClickListener{
                        dialogInterface, i ->
                        activity?.supportFragmentManager?.commit {
                            remove(this@NoteFragment)
                        }
                    })
                    setNegativeButton("No", null) //do nothing if the say no
                    create()
                    show()
                }

            }
        }
        //End of editButton

        //Edit button on hte right side
        val editButton = Button(requireContext()).apply {
            text = "edit"
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            (layoutParams as RelativeLayout.LayoutParams).addRule(

                RelativeLayout.LEFT_OF, deleteButton.id)

            setOnClickListener {
                val noteEditorDialog = NoteEditorDialog()
                noteEditorDialog.show(parentFragmentManager,
                    noteEditorDialog.tag)
                Log.d("NoteFragment", noteEditorDialog.tag.toString())
            }

        }
        //End of editButton


        val relativeLayout = RelativeLayout(requireContext()).apply {
            setBackgroundColor(Color.WHITE)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            addView(textHolderLL)
            addView(editButton)
            addView(deleteButton)
        }
        // Inflate the layout for this fragment
        return relativeLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            NoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}