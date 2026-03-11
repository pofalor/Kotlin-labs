//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val colors = listOf("Red", "Green", "Blue")
    println(colors.getOrDefaultValue(index = 2, defaultValue = 10))
}