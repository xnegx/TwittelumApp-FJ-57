package br.com.caelum.twittelumapp.activiy

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.caelum.twittelumapp.R
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actovoty_lista)

        val tweets = listOf("Meu primeiro tweet", "Meu segundo tweet", "Meu terceiro tweet")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tweets)

        lista_tweet.adapter = adapter
    }
}