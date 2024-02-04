package hw

data class Person(
    val name: String,
    val phone: MutableList<String> = mutableListOf(),
    val email: MutableList<String> = mutableListOf()
){
    override fun toString(): String {
        return """{"name": ${this.name}, "phone": ${this.phone}, "email": ${this.email}}"""
    }
}


