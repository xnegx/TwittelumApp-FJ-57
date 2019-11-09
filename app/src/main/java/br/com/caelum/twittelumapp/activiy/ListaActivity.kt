package br.com.caelum.twittelumapp.activiy

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bancodedados.TwittelumDatabase
import br.com.caelum.twittelumapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lista.*
import modelo.Tweet
import viewmodel.TweetViewModel
import viewmodel.ViewModelFactory

class ListaActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this,ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        viewModel.lista().observe(this, observer())

        fab_add.setOnClickListener {
            val intecao = Intent(this, TweetActivity::class.java)
            startActivity(intecao)
        }

        val listener = AdapterView.OnItemClickListener { adapter, item, posicao, id ->
            val tweet = lista_tweets.getItemAtPosition(posicao) as Tweet

            perguntaSePrecisaDeletarEsse(tweet)

        }

        lista_tweets.onItemClickListener = listener
    }

    private fun perguntaSePrecisaDeletarEsse(tweet: Tweet) {
        val dialog = AlertDialog.Builder(this
        )

        dialog.setMessage("Deseja mesmo apagar esse tweet ?")
        dialog.setTitle("Atencao")
        dialog.setPositiveButton("Sim") {_: DialogInterface, _: Int -> viewModel.deleta(tweet)}
        dialog.setNegativeButton("Não", null)
        dialog.show()
    }

    private fun observer() : Observer<List<Tweet>> {
        return Observer {
            lista_tweets.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,it)
        }
    }

}