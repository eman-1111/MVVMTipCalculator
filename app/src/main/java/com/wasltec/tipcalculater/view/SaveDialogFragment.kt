package com.wasltec.tipcalculater.view


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.EditText
import com.wasltec.tipcalculater.R

class SaveDialogFragment : DialogFragment() {

    private var saveTipCallback : SaveDialogFragment.Callback? = null

    interface Callback{
        fun onSaveTip(name : String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        saveTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val savedDialog =  context?.let {ctx ->
            val editText = EditText(ctx)
            editText.id = viewId
            editText.hint = getString(R.string.save_hint)

            AlertDialog.Builder(ctx)
                    .setView(editText)
                    .setNegativeButton(R.string.action_cancel, null)
                    .setPositiveButton(R.string.action_save,  {_,_ -> onSave(editText)})
                    .create()


        }

        return savedDialog!!

    }

    companion object {
        val viewId = View.generateViewId()
    }

    private fun onSave(editText: EditText){
        val text = editText.text.toString()
        if(!text.isEmpty()){
            saveTipCallback?.onSaveTip(text)
        }
    }
}