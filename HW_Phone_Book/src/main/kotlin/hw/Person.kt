package hw

data class Person(
    val name: String,
    val phones: MutableList<String> = mutableListOf(),
    val emails: MutableList<String> = mutableListOf())
