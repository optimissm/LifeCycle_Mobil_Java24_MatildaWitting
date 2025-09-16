package com.example.lifecycle.utils

class Validation {

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$")
        return password.matches(passwordPattern)
    }



}