package hw

import java.io.File
import kotlin.system.exitProcess

sealed interface Command {
    fun isValid(): Boolean
    fun run(persons: MutableMap<String, Person>)
    data object Exit : Command {
        override fun isValid(): Boolean {
            return true
        }
        override fun run(persons: MutableMap<String, Person>) {
            exitProcess(0)
        }
    }

    data object Help: Command {
        override fun isValid(): Boolean {
            return true
        }

        override fun run(persons: MutableMap<String, Person>) {
           println("Menu help:\n" +
                    "exit\n" +
                    "help\n" +
                    "find <Номер телефона>\n" +
                    "find <Адрес электронной почты>\n" +
                    "show <Имя>\n" +
                    "add <Имя> phone <Номер телефона>\n" +
                    "add <Имя> email <Адрес электронной почты>\n" +
                    "export <Путь к файлу>")
        }

    }

    data class AddPhone(val name: String, val phone: String): Command {
        override fun isValid(): Boolean {
            return this.phone.matches(Regex("""\+[0-9]+"""))
        }

         override fun run(persons: MutableMap<String, Person>){
            for (person in persons){
               if (person.key == this.name){
                   person.value.phones.add(phone)
                   println(person.value)
                   return
               }
            }
             persons[name] = Person(name = this.name, phones = mutableListOf(this.phone))
             println(persons[name])
         }
    }

    data class AddEmail(val name: String = "", val email: String =""): Command {
        override fun isValid(): Boolean {
                return this.email.matches(Regex("""[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]{2,}"""))
            }
        override fun run(persons: MutableMap<String, Person>){
            for (person in persons){
                if (person.key == this.name){
                    person.value.emails.add(email)
                    println(person.value)
                    return
                }
            }
            persons[name] = Person(name = this.name, emails = mutableListOf(this.email))
            println(persons[name])
        }
    }
    data class Show(val nameToFind: String): Command {
        override fun isValid(): Boolean {
            return true
        }
        override fun run(persons: MutableMap<String, Person>){
            for (person in persons){
                if (person.key == this.nameToFind){
                    println(person.value)
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
        override fun run(persons: MutableMap<String, Person>){
            for(person in persons){
                for (email in person.value.emails){
                    if (email == whatToFind) println(person.value)
                }
                for (phone in person.value.phones){
                    if (phone == whatToFind) println(person.value)
                }
            }
        }
    }
    data class Export(private val filePath: String): Command{
        override fun isValid(): Boolean {
            return true
        }
        override fun run(persons: MutableMap<String, Person>) {
             File(filePath).writeText(Json(persons).toString())
            //println(Json(persons).toString())
        }

    }
}