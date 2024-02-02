package hw

data class Person(
    val name: String,
    var phone: MutableList<String> = mutableListOf(),
    var email: MutableList<String> = mutableListOf()
)


