package classes

class InternetProfile (val name:String, val age:Int, val hobie:String?, val peopleRef:InternetProfile?) {
    fun funShowProfile() {
        println("Name: $name")
        println("Age: $age")
        println(if(peopleRef == null){
            "Likes to $hobie. Doesn't have a referrer"
        } else {
            "Likes to $hobie. Has a referrer named ${peopleRef.name}, who likes to ${peopleRef.hobie}"
        })
    }
}