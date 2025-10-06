//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

fun main() {
    val declarationWithType: String = "I'm"
    val declarationWithoutType = "Hooray!";
    println(declarationWithType)
    println("learning")
    println("Kotlin! $declarationWithoutType")

    val unreadCount = 5
    val readCount = 100
    println("You have ${unreadCount + readCount} total messages in your inbox.")

    // val keyword - Use when you expect the variable value will not change.
    // var keyword - Use when you expect the variable value can change.
    var cartTotal = 0
    cartTotal = 20
    println("Total: $cartTotal")

    println(birthdayGreeting("Rover", 5))
    println(birthdayGreeting(age = 2, name = "Rex"))
    println(birthdayGreeting("Alex"))
}

fun birthdayGreeting(name: String, age: Int = 10): String {
    val nameGreeting = "Happy Birthday, $name!"
    val ageGreeting = "You are now $age years old!"
    return "$nameGreeting\n$ageGreeting"
}