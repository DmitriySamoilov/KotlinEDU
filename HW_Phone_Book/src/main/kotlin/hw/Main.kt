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
    var resultCommand: Command
    when (commandLine[0].lowercase()) {
        "export" -> {
            if (commandLine.size == 2){
                resultCommand = Command.Export(commandLine[1])
                return resultCommand }
        }
        "exit" -> return Command.Exit
        "show" -> {
            if (commandLine.size > 1){
                resultCommand = Command.Show(name = commandLine[1])
                return resultCommand }
        }
        "find" -> {
            if (commandLine.size > 1){
            resultCommand = Command.Find(whatToFind = commandLine[1])
                return resultCommand }
        }
        "add" -> {
            if (commandLine.size == 4) {

                when (commandLine[2].lowercase()) {
                    "phone" -> {
                        resultCommand = Command.AddPhone(commandLine[1], commandLine[3])
                            return resultCommand
                    }

                    "email" -> {
                        resultCommand = Command.AddEmail(commandLine[1], commandLine[3])
                            return resultCommand
                    }
                }
            }
        }

    }
    return Command.Help
}


