package hw

fun main() {

    val persons: MutableMap<String, Person> = mutableMapOf()

    while (true) {
        print("Command: ")
        var cmd: Command = readCommand()
        when (cmd) {

            is Command.Exit -> {
                //println(cmd)
                return
            }

            is Command.AddPhone -> {
                //println(cmd)
                cmd.run(persons)
            }

            is Command.AddEmail -> {
                //println(cmd)
                cmd.run(persons)
            }

            is Command.Show -> {
                //println(cmd)
            cmd.run(persons)
            }
            is Command.Help -> {
                //println(cmd)
                Command.Help.run()
            }
            is Command.Find -> {
                cmd.run(persons)
            }
            else -> {
                println("Error")
            }
        }
    }
}

private fun readCommand(): Command {
    var commandLine = readln().split(" ")
    var resultCommand: Command
    when (commandLine[0].lowercase()) {
        "exit" -> return Command.Exit
        "show" -> {
            if (commandLine.size > 1){
                resultCommand = Command.Show(name = commandLine[1])
                if (resultCommand.isValid()) return resultCommand }
        }
        "find" -> {
            if (commandLine.size > 1){
            resultCommand = Command.Find(whatToFind = commandLine[1])

                if (resultCommand.isValid()) return resultCommand }
        }
        "add" -> {
            if (commandLine.size == 4) {

                when (commandLine[2].lowercase()) {
                    "phone" -> {
                        resultCommand = Command.AddPhone(commandLine[1], commandLine[3])
                        if (resultCommand.isValid())
                            return resultCommand
                    }

                    "email" -> {
                        resultCommand = Command.AddEmail(commandLine[1], commandLine[3])
                        if (resultCommand.isValid())
                            return resultCommand
                    }
                }
            }
        }

    }
    return Command.Help
}


