package classes

class Song (val title:String, val artist:String, val year:String, val numberRepro:Int) {

    val popular:String

    init {
        if(numberRepro < 1000){
            popular = "Isn´t popular"
        } else {
            popular = "Is popular"
        }
    }

    fun printData(){
        println("$title, interpretado por $artist, fue lanzado en $year")
    }
}