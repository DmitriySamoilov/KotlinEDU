package hw

data class Person(
    val name: String,
    val phones: MutableList<String> = mutableListOf(),
    val emails: MutableList<String> = mutableListOf()
){
    override fun toString(): String {
        return """{"name": ${this.name}, "phones": ${this.phones}, "emails": ${this.emails}}"""
    }
}


