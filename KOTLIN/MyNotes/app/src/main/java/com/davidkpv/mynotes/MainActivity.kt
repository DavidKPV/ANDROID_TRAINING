package com.davidkpv.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davidkpv.mynotes.databinding.ActivityMainBinding
import com.davidkpv.mynotes.main.MainViewModel
import com.davidkpv.mynotes.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter

    private val vm by viewModels<MainViewModel> {
        MainViewModelFactory(
            (application as NotesApplication).notesDatabse
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            notesAdapter = NotesAdapter(
                onNoteClick
            )

            vm.state.observe(this@MainActivity){ notes ->
                notesAdapter.submitList(notes)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.onResume();
    }
}