package hw

fun main() {

    val persons: MutableMap<String, Person> = mutableMapOf()
    while (true) {
        print("Command: ")
        var command: Command = readCommand()
        if (command.isValid()) command.run(persons) else Command.Help.run(persons)
    }
}

private fun readCommand(): Command {
    var commandLine = readln().split(" ")
    when (commandLine[0].lowercase()) {
        "export" -> if (commandLine.size == 2) return Command.Export(commandLine[1])
        "exit" -> return Command.Exit
        "show" -> if (commandLine.size == 2) return Command.Show(name = commandLine[1])
        "find" -> if (commandLine.size == 2) return Command.Find(whatToFind = commandLine[1])
        "add" -> {
            if (commandLine.size == 4) {
                when (commandLine[2].lowercase()) {
                    "phone" -> return Command.AddPhone(commandLine[1], commandLine[3])
                    "email" -> return Command.AddEmail(commandLine[1], commandLine[3])
                }
            }
        }

    }
    return Command.Help
}


