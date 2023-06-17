package contacts

import kotlin.system.exitProcess

class Contact(
    private var _name: String = "",
    private var _surname: String = "",
    private var _number: String = ""
) {
    var number: String
        get() {
            return if (!hasNumber()) {
                "[no number]"
            } else {
                _number
            }
        }
        set(value) {
            if (isValid(value))
                _number = value
            else
                _number = ""
                println("Wrong number format!")
        }

    var name: String
        get() = _name
        set(value) {
            _name = value
        }

    var surname: String
        get() = _surname
        set(value) {
            _surname = value
        }

    fun hasNumber(): Boolean {
        return this._number != ""
    }

    private fun isValid(input: String): Boolean {
        val numbRegex = "^\\+?(?:\\(?\\w+\\)?|\\(?\\w+\\)?[\\s-]\\w{2,}|\\w+[\\s-]\\(\\w{2,}\\))(?:[\\s-]\\w{2,})*\$".toRegex()
        return input.matches(numbRegex)
    }

}

fun count(list: MutableList<Contact>) {
    println("The Phone Book has ${list.size} records.")
}

fun add(list: MutableList<Contact>) {
    val newContact = Contact()
    println("Enter the name:")
    newContact.name = readln()
    println("Enter the surname:")
    newContact.surname = readln()
    println("Enter the number:")
    newContact.number = readln()
    println("The record added")
    list.add(newContact)
}

fun list(list: MutableList<Contact>) {
    for (i in 0 until list.size) {
        println("${i + 1}. ${list[i].name} ${list[i].surname}, ${list[i].number}")
    }
}

fun remove(list: MutableList<Contact>) {
    if (list.isEmpty()) {
        println("No records to remove!")
    } else {
        list(list)
        println("Select a record:")
        val recordToRemove = readln().toInt()
        list.removeAt(recordToRemove - 1)
        println("The record removed!")
    }
}

fun exit() {
    exitProcess(1)
}

fun edit(list: MutableList<Contact>) {
    if (list.isEmpty()) {
        println("No records to edit!")
    } else {
        list(list)
        println("Select a record:")
        val recordToEdit = readln().toInt()
        println("Select a field (name, surname, number):")
        when (readln()) {
            "name" -> {
                println("Enter name:")
                val newName = readln()
                list[recordToEdit - 1].name = newName
            }
            "surname" -> {
                println("Enter surname:")
                val newSurname = readln()
                list[recordToEdit - 1].surname = newSurname
            }
            "number" -> {
                println("Enter number:")
                val newNumber = readln()
                list[recordToEdit - 1].number = newNumber
            }
        }
        println("The record updated!")
    }
}

fun main() {

    val contactList = mutableListOf<Contact>()

    while (true) {
        println("Enter action (add, remove, edit, count, list, exit):")
        when (readln()) {
            "add" -> add(contactList)
            "remove" -> remove(contactList)
            "edit" -> edit(contactList)
            "count" -> count(contactList)
            "list" -> list(contactList)
            "exit" -> exit()
        }
    }
}