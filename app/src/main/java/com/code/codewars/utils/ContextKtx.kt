package com.code.codewars.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color.red
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.code.codewars.R
import timber.log.Timber

fun Context.showToast(@StringRes text: Int) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Activity.hideKeyboard() {
    val windowToken = this.window?.decorView?.rootView?.windowToken
    val imm =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (windowToken != null) {
        Timber.v("Keyboard hidden")
        imm.hideSoftInputFromWindow(windowToken, 0)
    } else {
        Timber.e("Unable to hide keyboard - no windowToken")
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.remove() {
    this.visibility = View.GONE
}

fun TextView.setSpannable(text: String, context: Context) {
    val rank = "Rank:"
    val span = SpannableStringBuilder(text)
    span.setSpan(
        ForegroundColorSpan(
            ContextCompat.getColor(
                context,
                R.color.red
            )
        ),
        rank.length,
        text.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = span
}
