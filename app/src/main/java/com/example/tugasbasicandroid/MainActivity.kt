package com.example.tugasbasicandroid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.tugasbasicandroid.data.Artikel
import com.example.tugasbasicandroid.adapter.ArtikelDetail

class MainActivity : AppCompatActivity() {
    private lateinit var rvArtikel: RecyclerView
    private val list = ArrayList<Artikel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Daftar Buah Yang Menyehatkan"
        rvArtikel = findViewById(R.id.rvArtikel)
        rvArtikel.setHasFixedSize(true)
        list.addAll(getListsArtikel())
        showLists()
}

    private fun showLists() {
        rvArtikel.layoutManager = LinearLayoutManager(this)
        val listArtikelAdapter = ArtikelDetail(list)
        rvArtikel.adapter = listArtikelAdapter
        listArtikelAdapter.setOnItemClickCallback(object : ArtikelDetail.OnItemClickCallback {
            override fun onItemClicked(data: Artikel) {
                Detailartikel(data)
            }
        })
    }

    @SuppressLint("Recycle")
    private fun getListsArtikel(): Collection<Artikel> {
        val dataName = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_deskripsi)
        val dataPhoto = resources.obtainTypedArray(R.array.data_thumbnail)
        val listArtikel = ArrayList<Artikel>()
        for (i in dataName.indices) {
            val artikel = Artikel(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listArtikel.add(artikel)
        }
        return listArtikel
    }
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun Detailartikel(artikel: Artikel) {
        val intent = Intent(this@MainActivity,DetailActivity::class.java)
        intent.putExtra("judul",artikel.nama)
        intent.putExtra("deskripsi",artikel.deskripsi)
        intent.putExtra("gambar",artikel.gambar)
        startActivity(intent)
    }
}