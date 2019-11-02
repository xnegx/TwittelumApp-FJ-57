package br.com.caelum.twittelumapp.activiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import bancodedados.TwittelumDatabase
import br.com.caelum.twittelumapp.R
import modelo.Tweet
import viewmodel.TweetViewModel
import viewmodel.ViewModelFactory

class TweetActivity : AppCompatActivity() {


    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this,ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.tweet_menu_cadastrar -> {
                publicaTweet()
                finish()
            }

            android.R.id.home -> finish()
        }

        return false
    }


    private fun publicaTweet() {

        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.tweet_mensagem)
        val mensagemDoTweet : String = campoDeMensagemDoTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)

        viewModel.salva(tweet)

        Toast.makeText(this, "${tweet.mensagem} foi salvo com sucesso :D", Toast.LENGTH_LONG).show()
    }
}
