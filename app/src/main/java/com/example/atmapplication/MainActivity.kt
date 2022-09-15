package com.example.atmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atmapplication.databinding.ActivityMainBinding
import com.example.atmapplication.db.NotesDataBase
import com.example.atmapplication.dto.Notes
import com.example.atmapplication.repository.NotesRepository
import com.example.atmapplication.viewmodel.NotesViewModel
import com.example.atmapplication.viewmodel.NotesViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NotesViewModel
    private val noteAdapater: NotesRecylerView = NotesRecylerView(arrayListOf())
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateRecylerView()

        val notesRepository = NotesRepository(NotesDataBase(this))
        val viewModelProviderFactory = NotesViewModelFactory(notesRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory)[NotesViewModel::class.java]
        viewModel.getSavedNotes().observe(this, Observer {
            noteAdapater.updateNotes(it)
        })
        viewModel.getSavedNotes()
        binding.button.setOnClickListener {
            try {
                var amountEntered = Integer.parseInt(binding.enterAmountText.text.toString())

                if (amountEntered % 100 != 0) {
                    Snackbar.make(
                        binding.root,
                        "Enter Valid Amount in multiple of 100,200,500,2000",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    lifecycleScope.launch(Dispatchers.IO) {
                        Snackbar.make(
                            binding.root,
                            "Processing Transaction Please wait...",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        delay(5000)
                        Snackbar.make(
                            binding.root,
                            "Transaction Successful...",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                        withContext(Dispatchers.Main) {
                            binding.mainAtmBalanceT.text = amountEntered.toString()
                            withdraw(amountEntered)
                        }
                    }
                }
            } catch (e:Exception) {
                Snackbar.make(
                    binding.root,
                    "Enter Valid Amount only in Numbers",
                    Snackbar.LENGTH_SHORT
                )
            }
        }
    }

    private fun updateRecylerView() {
        binding.transRecyler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapater
        }
    }

   private fun withdraw(amount: Int){
        var moneyValue = amount
        var hundrad = 0
        var twoHundrad = 0
        var fiveHundrad = 0
        var twoThousand = 0
        var newValue = intArrayOf()
        var newArrw = intArrayOf()
        val noteValues = intArrayOf(2000, 500, 200, 100)
        if (moneyValue > 10000) {
            Snackbar.make(binding.root, "Limit Exceeded", Snackbar.LENGTH_SHORT).show()
        } else {
            var i = 0
            while (i < noteValues.size && moneyValue != 0) {
                newValue = intArrayOf(noteValues[i])
                if (newValue.contains(2000)) {
                    binding.mainAtm2000T.text = (moneyValue / noteValues[i]).toString()
                    twoThousand = (moneyValue / noteValues[i])
                }
                if (newValue.contains(500)) {
                    binding.mainAtm500T.text = (moneyValue / noteValues[i]).toString()
                    fiveHundrad = (moneyValue / noteValues[i])
                }
                if (newValue.contains(200)) {
                    binding.mainAtm200T.text = (moneyValue / noteValues[i]).toString()
                    twoHundrad = (moneyValue / noteValues[i])
                }
                if (newValue.contains(100)) {
                    binding.mainAtm100T.text = (moneyValue / noteValues[i]).toString()
                    hundrad = (moneyValue / noteValues[i])
                }
                moneyValue %= noteValues[i]
                i++
            }
            viewModel.saveNotesToDb( Notes(amount.toString(),hundrad.toString(),twoHundrad.toString(),fiveHundrad.toString(),twoThousand.toString()))

        }
    }
}
