package com.jeluchu.core.extensions

import org.bson.Document
import java.text.SimpleDateFormat
import java.util.*

fun Document.getStringSafe(key: String, defaultValue: String = ""): String {
    return try {
        when (val value = this[key]) {
            is String -> value
            is Number, is Boolean -> value.toString()
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

fun Document.getIntSafe(key: String, defaultValue: Int = 0): Int {
    return try {
        when (val value = this[key]) {
            is Int -> value
            is Number -> value.toInt()
            is String -> value.toIntOrNull() ?: defaultValue
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

fun Document.getDoubleSafe(key: String, defaultValue: Double = 0.0): Double {
    return try {
        when (val value = this[key]) {
            is Double -> value
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull() ?: defaultValue
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

fun Document.getFloatSafe(key: String, defaultValue: Float = 0.0f): Float {
    return try {
        when (val value = this[key]) {
            is Float -> value
            is Number -> value.toFloat()
            is String -> value.toFloatOrNull() ?: defaultValue
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

fun Document.getLongSafe(key: String, defaultValue: Long = 0L): Long {
    return try {
        val value = this.get(key)
        when (value) {
            is Long -> value
            is Number -> value.toLong()
            is String -> value.toLongOrNull() ?: defaultValue
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

fun Document.getBooleanSafe(key: String, defaultValue: Boolean = false): Boolean {
    return try {
        when (val value = this[key]) {
            is Boolean -> value
            is String -> value.toBoolean()
            is Number -> value.toInt() != 0
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

fun Document.getDateSafe(key: String, defaultValue: Date = Date(0)): Date {
    return try {
        when (val value = this[key]) {
            is Date -> value
            is String -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(value) ?: defaultValue
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

inline fun <reified T> Document.getListSafe(key: String, defaultValue: List<T> = emptyList()): List<T> {
    return try {
        when (val value = this[key]) {
            is List<*> -> value.filterIsInstance<T>()
            else -> defaultValue
        }
    } catch (e: Exception) {
        defaultValue
    }
}

fun Document.getDocumentSafe(key: String): Document? {
    return try {
        val value = this[key]
        if (value is Document) value else null
    } catch (e: Exception) {
        null
    }
}