package br.com.caelum.twittelumapp.activiy

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.caelum.twittelumapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val tweets = listOf("Bla", "Ble", "Bli", "Blo", "Blu")

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tweets)

        lista_tweets.adapter = adapter

        fab_add.setOnClickListener { Snackbar.make(it,"FAB clicado", Snackbar.LENGTH_LONG).show() }
    }
}