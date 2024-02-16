package com.example.core.common

class GeneralException(
    val code: String? = "",
    override val message: String? = ""
) : Exception()