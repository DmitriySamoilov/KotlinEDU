fun main(args: Array<String>) {

    val persons: MutableList<Person> = mutableListOf()

    while (true) {
        print("Command: ")
        var command: Command = readCommand()
        when (command) {

            is Command.Exit -> {
                println(command)
                return
            }

            is Command.AddPhone -> {
                println(command)
                persons.add(command.run())
            }

            is Command.AddEmail -> {
                println(command)
                persons.add(command.run())
            }

            is Command.Show -> {println(command)
            println (if (persons.size > 0) persons[persons.size-1].toString() else "Not initialized")
            }
            is Command.Help -> {
                println(command)
                Command.Help.run()
            }

            else -> {
                println("Error")
            }
        }
    }
}

fun readCommand(): Command {
    var commandLine = readln().split(" ")

    when (commandLine[0].lowercase()) {
        "exit" -> return Command.Exit
        "show" -> return Command.Show
        "add" -> {
            if (commandLine.size == 4) {
                when (commandLine[2].lowercase()) {
                    "phone" -> {
                        if (Command.AddPhone.isValid(commandLine[3]))
                            return Command.AddPhone(commandLine[1], commandLine[3])
                    }

                    "email" -> {
                        if (Command.AddEmail.isValid(commandLine[3]))
                            return Command.AddEmail(commandLine[1], commandLine[3])
                    }
                }
            }
        }

    }
    return Command.Help
}


