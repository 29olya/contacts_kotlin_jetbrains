package contacts

class Contact(
    var name: String = "",
    var surname: String = "",
    var number: String = ""
) {
    fun createRecord(): Contact {
        println("Enter the name of the person:")
        val name = readln()
        println("Enter the surname of the person:")
        val surname = readln()
        println("Enter the number:")
        val number = readln()
        println("A record created!")
        println("A Phone Book with a single record created!")
        return Contact(name, surname, number)
    }
}

fun main() {
    val firstRecord = Contact().createRecord()
}