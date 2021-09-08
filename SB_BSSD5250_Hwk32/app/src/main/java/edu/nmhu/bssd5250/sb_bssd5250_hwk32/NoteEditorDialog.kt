package edu.nmhu.bssd5250.sb_bssd5250_hwk32

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult

class NoteEditorDialog : DialogFragment() {

    private var targetResultKey:String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val editName = EditText(context).apply {
            setHint(R.string.name_place_holder)
            setText(existingData.getString("name"))
        }
        val editDate = EditText(context).apply {
            setHint(R.string.date_place_holder)
            setText(existingData.getString("date"))
        }
        val editDesc = EditText(context).apply {
            setHint(R.string.desc_place_holder)
            setText(existingData.getString("desc"))
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
                setFragmentResult(targetResultKey,
                    bundleOf("nameKey" to editName.text.toString(),
                                    "dateKey" to editDate.text.toString(),
                                    "descKey" to editDesc.text.toString()
                    ))

                /*Log.d("NED: Name",editName.text.toString())
                Log.d("NED: Date",editDate.text.toString())
                Log.d("NED: Desc",editDesc.text.toString())*/
            }
            setNegativeButton("Cancel") { _,_ -> }
        }
        return ad.create()
    }

    companion object {
        const val TAG = "NoteEditorDialog"
        var existingData:Bundle = Bundle.EMPTY

        @JvmStatic
        fun newInstance(target:String, existing:Bundle) =
            NoteEditorDialog().apply {
                targetResultKey = target
                existingData = existing
            }
    }
}

