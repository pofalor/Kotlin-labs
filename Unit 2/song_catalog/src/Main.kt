//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Song(
    private val title: String,
    private val artist: String,
    private val yearPublished: Int,
    private val playCount: Int) {

    val isPopular: Boolean
        public get() {return playCount >= 1000}

    fun printInfo(){
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}

fun main() {
    val song = Song("Последний", "Lizer", 2018, 24733)
    song.printInfo()
}