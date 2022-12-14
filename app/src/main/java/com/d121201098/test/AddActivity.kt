package com.d121201098.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.d121201098.test.databinding.ActivityAddBinding
import com.d121201098.test.model.Task
import com.d121201098.test.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddBinding
    private lateinit var kategoriTugas:ArrayAdapter<CharSequence>
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        kategoriTugas = ArrayAdapter.createFromResource(this,R.array.kategori_tugas,android.R.layout.simple_list_item_1)
        binding.dropdownKategoriTugas.setAdapter(kategoriTugas)

        binding.btnAdd.setOnClickListener {
            val judul = binding.judulTugas.text.toString()
            val isi = binding.isiTugas.text.toString()
            val kategori = binding.dropdownKategoriTugas.text.toString()

            if (kategori.isEmpty()) {
                Toast.makeText(this, "Kategori Belum dipilih", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (judul.isEmpty()) {
                binding.judulTugas.error = "Judul tidak boleh kosong"
                binding.judulTugas.requestFocus()
                return@setOnClickListener
            }
            if (isi.isEmpty()) {
                binding.isiTugas.error = "Deskripsi tidak boleh kosong"
                binding.isiTugas.requestFocus()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val task = Task(0,judul,isi,kategori,"Belum Selesai")
                taskViewModel.addTask(task)
                finish()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}