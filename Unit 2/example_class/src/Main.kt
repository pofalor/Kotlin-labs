import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    open fun isOn(): Boolean {
        return deviceStatus == "on"
    }

    open fun isOff(): Boolean {
        return deviceStatus == "off"
    }

    fun printDeviceInfo(){
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }

    fun decreaseVolume(){
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume.")
    }

    fun previousChannel() {
        channelNumber--
        println("Channel number decreased to $channelNumber.")
    }

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness decreased to $brightnessLevel.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        if(smartTvDevice.isOn()){
            smartTvDevice.increaseSpeakerVolume()
        }
        else if(smartLightDevice.isOff()){
            println("Unable to increase TV volume, device is turned off")
        }
    }

    fun changeTvChannelToNext() {
        if(smartTvDevice.isOn()) {
            smartTvDevice.nextChannel()
        }
        else if(smartLightDevice.isOff()){
            println("Unable to change TV channel, device is turned off")
        }
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        if(smartLightDevice.isOn()){
            smartLightDevice.increaseBrightness()
        }
        else if(smartLightDevice.isOff()){
            println("Unable to increase brightness, device is turned off")
        }
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }

    fun decreaseTvVolume(){
        smartTvDevice.decreaseVolume()
    }

    fun changeTvChannelToPrevious(){
        smartTvDevice.previousChannel()
    }

    fun printSmartTvInfo(){
        smartTvDevice.printDeviceInfo()
    }

    fun printSmartLightInfo(){
        smartLightDevice.printDeviceInfo()
    }

    fun decreaseLightBrightness(){
        smartLightDevice.decreaseBrightness()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    val smartTvDevice: SmartTvDevice = SmartTvDevice("Android TV", "Entertainment")
    smartTvDevice.turnOn()


    val smartLightDevice = SmartLightDevice("Google Light", "Utility")
    smartLightDevice.turnOn()

    val smartHome: SmartHome = SmartHome(smartTvDevice, smartLightDevice)
    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToPrevious()
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
    smartHome.decreaseLightBrightness()
}