package hw


data class Json(val collection: MutableMap<String, Person>) {
    override fun toString() = buildString {
        append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            append(jsonPerson(iterator.next().value))
            if (iterator.hasNext()) append(",")
        }
        append("]")
    }
    private fun jsonPerson(person: Person) = """{"name": ${jsonString(person.name)}, "phones": ${jsonArray(person.phones)}, "emails": ${jsonArray(person.emails)}}"""


    private fun jsonArray(list:MutableList<String>) = buildString {
        append("[")
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            append(jsonString(iterator.next()))
            if (iterator.hasNext()) append(",")
        }
        append("]")
    }

    private fun jsonString(text:String) = "\"$text\""

}

