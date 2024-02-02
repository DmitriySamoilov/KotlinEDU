package hw

sealed interface Command {
    fun isValid(): Boolean
    //fun run():
    data object Exit : Command {
        override fun isValid(): Boolean {
            return true
        }
    }

    data object Help: Command {
        override fun isValid(): Boolean {
            return true
        }
        fun run(){
            println("Menu:\n" +
                    "exit\n" +
                    "help\n" +
                    "find <Номер телефона>\n" +
                    "find <Адрес электронной почты>\n" +
                    "show <Имя>\n" +
                    "add <Имя> phone <Номер телефона>\n" +
                    "add <Имя> email <Адрес электронной почты>")
        }

    }

    data class AddPhone(val name: String, val phone: String): Command {
        override fun isValid(): Boolean {
            return this.phone.matches(Regex("""\+[0-9]+"""))
        }

         fun run(persons: MutableMap<String, Person>){
            for (person in persons){
               if (person.key == this.name){
                   person.value.phone.add(phone)
                   println(person)
                   return
               }
            }
             persons[name] = Person(name = this.name, phone = mutableListOf(this.phone))
             println(persons[name])
         }
    }

    data class AddEmail(val name: String = "", val email: String =""): Command {
        override fun isValid(): Boolean {
                return this.email.matches(Regex("""[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]{2,}"""))
            }
        fun run(persons: MutableMap<String, Person>){
            for (person in persons){
                if (person.key == this.name){
                    person.value.email.add(email)
                    println(person)
                    return
                }
            }
            persons[name] = Person(name = this.name, email = mutableListOf(this.email))
            println(persons[name])
        }
    }
    data class Show(val name: String): Command {
        override fun isValid(): Boolean {
            return true
        }
        fun run(persons: MutableMap<String, Person>){
            for (person in persons){
                if (person.key == this.name){
                    println(person)
                    return
                }
            }
            println ( "Not initialized")
        }
    }

    data class Find(val whatToFind: String) : Command {
        override fun isValid(): Boolean {
            return true
        }
        fun run(persons: MutableMap<String, Person>){
            for(person in persons){
                for (email in person.value.email){
                    if (email == whatToFind) println(person.value)
                }
                for (phone in person.value.phone){
                    if (phone == whatToFind) println(person.value)
                }
            }
        }
    }
}