package com.media2359.weatherapp.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.media2359.weatherapp.R
import com.media2359.weatherapp.databinding.DialogSingleButtonBinding

fun Context.showProgressDialog(): Dialog {
    return Dialog(this, R.style.TransparentDialog).apply {
        setContentView(R.layout.view_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}

fun Context.createAlertDialog(
    mes: String,
    titleBtOk: String = getString(R.string.action_ok),
    cancelable: Boolean = false,
    onPositiveBtnClick: (Dialog) -> Unit = {}
): Dialog {
    val inflater = LayoutInflater.from(this)
    val binding = DialogSingleButtonBinding.inflate(inflater)
    val dialogView = binding.root

    val dialog = AlertDialog.Builder(this)
        .apply {
            setView(dialogView)
            setCancelable(cancelable)
        }
        .create()
        .apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }

    dialogView.run {
        binding.run {
            tvMessage.text = mes
            btOk.text = titleBtOk

            btOk.setOnClickListener {
                onPositiveBtnClick(dialog)
                dialog.dismiss()
            }
        }
    }

    dialog.show()
    return dialog
}