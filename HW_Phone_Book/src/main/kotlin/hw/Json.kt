package hw


data class Json(val collection: MutableMap<String, Person>) {
    override fun toString() = buildString {
        append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            append(iterator.next().value)
            if (iterator.hasNext()) append(",")
        }
        append("]")
    }

}

