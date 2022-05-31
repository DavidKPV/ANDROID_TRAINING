package mx.com.davidkpv.exampleviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var urlImage : MutableLiveData<String?>? = null

    fun callUrlImage() : MutableLiveData<String?>?{
        if(urlImage == null){
            urlImage = MutableLiveData<String?>()
            randomUrl()
        }
        return urlImage
    }

    fun randomUrl(){
        urlImage?.value = "https://picsum.photos/${(3..5).random()}00/${(3..5).random()}00"
    }

}