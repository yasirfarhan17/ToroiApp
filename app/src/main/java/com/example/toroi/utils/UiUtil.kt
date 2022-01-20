package com.example.toroi.utils

import android.content.Context
import android.widget.Toast
import com.example.toroi.ui.base.BaseActivity
import com.google.android.material.snackbar.Snackbar

class UiUtil(private val context: Context) {

    private lateinit var snackbar: Snackbar

    fun showMessage(
        message: String,
        length: Int = Snackbar.LENGTH_SHORT
    ) {
        showSnackBar(message, length)
    }

    fun showToast(
        message: String,
        length: Int = Toast.LENGTH_SHORT
    ) {
        Toast.makeText(context, message, length).show()
    }

    fun showProgress() {
    }

    fun hideProgress() {
    }

    private fun showSnackBar(
        message: String,
        snackBarLength: Int = Snackbar.LENGTH_LONG
    ) {
        if (!::snackbar.isInitialized) {
            (context as BaseActivity<*, *>).getLayoutBinding()
                .root.let {
                    snackbar = Snackbar.make(it, message, snackBarLength)
                }
        }
        snackbar.setText(message)
        snackbar.show()
    }

}
