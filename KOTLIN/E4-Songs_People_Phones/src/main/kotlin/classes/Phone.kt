package classes

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if(isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class Foldablephone(private var isFolding: Boolean = false) : Phone() {

    override fun switchOn(){
        isScreenLightOn = !isFolding
    }

    fun setFolding(value: Boolean){
        isFolding = value
    }

}
