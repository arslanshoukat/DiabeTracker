package com.alharoof.diabetracker.extensions

import android.widget.EditText

fun EditText.intTextOrNull(): Int? = this.text.toString().toIntOrNull()

fun EditText.isTextNotZero(): Boolean = this.text.isNotBlank() && (this.text.toString().toIntOrNull() ?: 0) != 0
