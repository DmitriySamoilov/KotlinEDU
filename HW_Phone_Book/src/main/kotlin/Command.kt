sealed interface Command {

    data object Exit : Command {

        fun isValid(command: String): Boolean {
            return true
        }

        fun run() {
            TODO("Not yet implemented")
        }
    }

    data object Help: Command{
        fun isValid(command: String): Boolean {
            return true
        }
        fun run(){
            println("Menu:\n" +
                    "exit\n" +
                    "help\n" +
                    "show\n" +
                    "add <Имя> phone <Номер телефона>\n" +
                    "add <Имя> email <Адрес электронной почты>")
        }

    }

    data class AddPhone(val name: String, val phone: String): Command{


         fun run(): Person {
            return Person(name = this.name, phone = this.phone, email = "")
        }

        companion object : Command {
            fun isValid(command: String): Boolean {
                return command.matches(Regex("""\+[0-9]+"""))
            }
        }

    }

    data class AddEmail(var name: String = "", var email: String =""): Command{

        companion object : Command {
            fun isValid(command: String): Boolean {
                return command.matches(Regex("""[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]{2,}"""))
            }
        }

        fun run(): Person {
            return Person(name = this.name, phone = "", email = this.email)
        }
    }
    data object Show: Command{
        fun isValid(command: String): Boolean {
            return true
        }

        fun run() {
            TODO("Not yet implemented")
        }

    }

}