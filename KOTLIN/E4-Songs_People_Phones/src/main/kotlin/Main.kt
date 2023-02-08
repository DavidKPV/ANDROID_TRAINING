import classes.Foldablephone
import classes.InternetProfile
import classes.Song

fun main(args: Array<String>) {

    println("------ Exercise SONGS ------")
    songs()

    println("------ Exercise PEOPLE ------")
    people()

    println("------ Exercise PHONES ------")
    phone()

}

fun songs(){
    val song = Song( "BAD", "Michael Jackson", "1990", 100000)
    println(song.numberRepro)
    println(song.popular)
    song.printData()
}

fun people(){
    val amanda = InternetProfile("Amanda", 33, "play tennis", null)
    val atiqah = InternetProfile("Atiqah", 28, "climb", amanda)

    amanda.funShowProfile()
    atiqah.funShowProfile()
}

fun phone(){
    // Phone closed
    val phoneFold = Foldablephone(true)
    phoneFold.checkPhoneScreenLight()

    // Phone open
    phoneFold.setFolding(false)
    phoneFold.switchOn()
    phoneFold.checkPhoneScreenLight()

    // Phone open
    phoneFold.setFolding(false)
    phoneFold.switchOff()
    phoneFold.checkPhoneScreenLight()

    // Phone closed
    phoneFold.setFolding(true)
    phoneFold.switchOn()
    phoneFold.checkPhoneScreenLight()
}