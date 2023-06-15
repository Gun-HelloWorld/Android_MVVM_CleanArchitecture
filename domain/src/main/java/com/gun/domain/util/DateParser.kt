package com.gun.domain.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateParser {
    @Suppress("ConstantLocale")
    private val MARVEL_API_RESPONSE_DATE_FORMAT = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())

    @Suppress("SimpleDateFormat")
    val SEARCH_DATE_FORMAT = SimpleDateFormat("yyyy.mm.dd")

    fun parseToDateFormatString(inputDateString: String?, outputDateStringFormat: SimpleDateFormat): String? {
        var result: String? = null

        try {
            val date = MARVEL_API_RESPONSE_DATE_FORMAT.parse(inputDateString)
            result = outputDateStringFormat.format(date)
            println("parseToDateFormatString() - result : $result")
        } catch (parseException: ParseException) {
            println("parseToDateFormatString() - parseException : ${parseException.message}")
        } catch (nullPointerException: NullPointerException) {
            println("parseToDateFormatString() - NullPointerException : ${nullPointerException.message}")
        }

        return result
    }

}