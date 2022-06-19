package com.example.compose.utils

import android.provider.ContactsContract
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.example.compose.model.Person
import kotlinx.coroutines.ExperimentalCoroutinesApi

fun mobileNumberFilter(text: AnnotatedString): TransformedText {
    val mask = "xx xxx xx xx"

    val trimmed =
        if (text.text.length >= 9) text.text.substring(0..8) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 1 || i == 4 || i == 6) {
                append(" ")
            }
        }
        pushStyle(SpanStyle(color = Color.LightGray))
        append(mask.takeLast(mask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset + 1
            if (offset <= 6) return offset + 2
            if (offset <= 9) return offset + 3
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset - 1
            if (offset <= 6) return offset - 2
            if (offset <= 9) return offset - 3
            return 9
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun initContacts() {
    if (checkPermission(READ_CONT)) {
        ARRAY_CONTACTS = arrayListOf()
        val cursor = MAIN_ACT.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            while (it.moveToNext()) {
                val fullName =
                    it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                val phone =
                    it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val newPhonePerson = Person()
                newPhonePerson.Name = fullName
                newPhonePerson.Phone = phone.replace(Regex("[\\s,-]"), "")
                ARRAY_CONTACTS.add(newPhonePerson)

            }
        }
        cursor?.close()
        personPhoneNumbers(ARRAY_CONTACTS)
    }
}

fun personPhoneNumbers(arrayCont: ArrayList<Person>): Array<String> {
    val array: Array<String> = Array((arrayCont.size)) { arrayCont[1].toString() }
    var i = 0
    arrayCont.forEach { contact ->
        array[i] = "${contact.Phone} (${contact.Name})"
        i += 1
    }
    return array
}