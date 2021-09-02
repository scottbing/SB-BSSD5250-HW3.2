package edu.nmhu.bssd5250.sb_bssd5250_hwk32

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult

class NoteEditorDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val editName = EditText(context).apply {
            setText(R.string.name_place_holder)
        }
        val editDate = EditText(context).apply {
            setText(R.string.date_place_holder)
        }
        val editDesc = EditText(context).apply {
            setText(R.string.desc_place_holder)
        }

        val linearLayout = LinearLayoutCompat(requireContext()).apply{
            orientation = LinearLayoutCompat.VERTICAL
            addView(editName)
            addView(editDate)
            addView(editDesc)
        }

        val ad = AlertDialog.Builder(requireContext()).apply {
            setTitle("Note Editor")
            setMessage("Edit Content")
            setView(linearLayout)
            setPositiveButton("Save") { _,_ ->
                val result = editName.text.toString()
                setFragmentResult("noteDataChange",
                    bundleOf("nameKey" to result))
            }
            setNegativeButton("Cancel") { _,_ -> }
        }
        return ad.create()
    }

    companion object {
        const val TAG = "NoteEditorDialog"
        @JvmStatic
        fun newInstance() =
            NoteEditorDialog().apply {

            }
    }
}