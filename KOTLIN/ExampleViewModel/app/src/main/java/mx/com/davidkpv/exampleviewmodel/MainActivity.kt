package mx.com.davidkpv.exampleviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import mx.com.davidkpv.exampleviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // ViewModel
        var model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        var urlImage : MutableLiveData<String?>? = model.callUrlImage()

        urlImage?.observe(this, Observer {
            // Charge the Image
            Picasso.get().load(it).into(binding.imageView)
        })

        binding.button.setOnClickListener {
            model.randomUrl()
        }
    }
}