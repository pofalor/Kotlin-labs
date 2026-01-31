open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    open fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(isScreenLightOn: Boolean = false, var isFolded : Boolean = false) : Phone(isScreenLightOn) {
    override fun switchOn() {
        foldOn()
    }

    override fun switchOff() {
        foldOff()
    }

    fun foldOn(){
        isFolded = true
    }

    fun foldOff(){
        isFolded = false
    }
}

fun main(){
    val phone = FoldablePhone()
    phone.switchOn()
    phone.foldOff()
}