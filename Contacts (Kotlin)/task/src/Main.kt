package contacts

import kotlin.system.exitProcess
import java.time.LocalDateTime

open class Contact(
    private var _number: String = "",
    val timeOfCreation: LocalDateTime = LocalDateTime.now(),
    var timeOfLastEdit: LocalDateTime = LocalDateTime.now(),
    var isPerson: Boolean = true
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
            _number = if (isValid(value))
                value
            else
                ""
                println("Wrong number format!")
        }

    private fun hasNumber(): Boolean {
        return this._number != ""
    }

    private fun isValid(input: String): Boolean {
        val numbRegex = "^\\+?(?:\\(?\\w+\\)?|\\(?\\w+\\)?[\\s-]\\w{2,}|\\w+[\\s-]\\(\\w{2,}\\))(?:[\\s-]\\w{2,})*\$".toRegex()
        return input.matches(numbRegex)
    }
}

class Person(
    private var _name: String = "",
    private var _surname: String = "",
    private var _birthDate: String = "",
    private var _gender: String = ""
):Contact() {
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

    var birthDate: String
        get() {
            return if (_birthDate == "") {
                "[no data]"
            } else {
                _birthDate
            }
        }
        set(value) {
            _birthDate = if (value != "")
                value
            else
                ""
                println("Bad birth date!")
        }

    var gender: String
        get() {
            return if (_gender == "") {
                "[no data]"
            } else {
                _gender
            }
        }
        set(value) {
            _gender = if (value == "M" || value == "F")
                value
            else
                ""
                println("Bad gender!")
        }

    fun addRecord() {
        println("Enter the name:")
        this.name = readln()
        println("Enter the surname:")
        this.surname = readln()
        println("Enter the birth date:")
        this.birthDate = readln()
        println("Enter the gender (M, F):")
        this.gender = readln()
        println("Enter the number:")
        this.number = readln()
        this.isPerson = true
    }

    fun editRecord() {
        println("Select a field (name, surname, birth, gender, number):")
        when (readln()) {
            "name" -> {
                println("Enter the name")
                this.name = readln()
            }
            "surname" -> {
                println("Enter the surname:")
                this.surname = readln()
            }
            "birth" -> {
                println("Enter the birth date:")
                this.birthDate = readln()
            }
            "gender" -> {
                println("Enter the gender (M, F):")
                this.gender = readln()
            }
            "number" -> {
                println("Enter the number:")
                this.number = readln()
            }
        }
        this.timeOfLastEdit = LocalDateTime.now()
    }
}

class Organization(
    private var _name: String = "",
    private var _address: String = ""
): Contact() {

    var name: String
        get() = _name
        set(value) {
            _name = value
        }

    var address: String
        get() = _address
        set(value) {
            _address = value
        }

    fun addRecord() {
        println("Enter the organization name:")
        this.name = readln()
        println("Enter the address:")
        this.address = readln()
        println("Enter the number:")
        this.number = readln()
        this.isPerson = false
    }

    fun editRecord() {
        println("Select a field (address, number):")
        when (readln()) {
            "address" -> {
                println("Enter the address:")
                this.address = readln()
            }
            "number" -> {
                println("Enter the number:")
                this.number = readln()
            }
        }
        this.timeOfLastEdit = LocalDateTime.now()
    }
}

fun count(list: MutableList<Contact>) {
    println("The Phone Book has ${list.size} records.")
}

fun add(list: MutableList<Contact>) {
    println("Enter the type (person, organization):")
    when (readln()) {
        "person" -> {
            val newContact = Person()
            newContact.addRecord()
            list.add(newContact)
        }
        "organization" -> {
            val newContact = Organization()
            newContact.addRecord()
            list.add(newContact)
        }
    }
    println("The record added.")
    println()
}

fun listOfContacts(list: MutableList<Contact>) {
    for (i in 0 until list.size) {
        if (list[i].isPerson) {
            val record =  list[i] as Person
            println("${i + 1}. ${record.name} ${record.surname}")
        } else {
            val record = list[i] as Organization
            println("${i + 1}. ${record.name}")
        }
    }
}

fun info(list: MutableList<Contact>) {
    listOfContacts(list)
    println("Enter index to show info:")
    val indexToShowInfo = readln().toInt()
    if (list[indexToShowInfo - 1].isPerson) {
        val showInfo = list[indexToShowInfo - 1] as Person
        println("Name: ${showInfo.name}")
        println("Surname: ${showInfo.surname}")
        println("Birth date: ${showInfo.birthDate}")
        println("Gender: ${showInfo.gender}")
        println("Number: ${showInfo.number}")
        println("Time created: ${showInfo.timeOfCreation}")
        println("Time last edit: ${showInfo.timeOfLastEdit}")
    } else {
        val showInfo = list[indexToShowInfo - 1] as Organization
        println("Organization name: ${showInfo.name}")
        println("Address: ${showInfo.address}")
        println("Number: ${showInfo.number}")
        println("Time created: ${showInfo.timeOfCreation}")
        println("Time last edit: ${showInfo.timeOfLastEdit}")
    }
    println()
}

fun remove(list: MutableList<Contact>) {
    if (list.isEmpty()) {
        println("No records to remove!")
    } else {
        listOfContacts(list)
        println("Select a record:")
        val recordToRemove = readln().toInt()
        list.removeAt(recordToRemove - 1)
        println("The record removed!")
    }
    println()
}

fun edit(list: MutableList<Contact>) {
    if (list.isEmpty()) {
        println("No records to edit!")
    } else {
        listOfContacts(list)
        println("Select a record:")
        val recordToEdit = readln().toInt()
        if (list[recordToEdit - 1].isPerson) {
            val editInfo = list[recordToEdit - 1] as Person
            editInfo.editRecord()
        } else {
            val editInfo = list[recordToEdit - 1] as Organization
            editInfo.editRecord()
        }
    }
    println("The record updated!")
    println()
}

fun exit() {
    exitProcess(1)
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
            "info" -> info(contactList)
            "exit" -> exit()
        }
    }
}