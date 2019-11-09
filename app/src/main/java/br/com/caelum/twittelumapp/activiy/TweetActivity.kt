package br.com.caelum.twittelumapp.activiy

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import bancodedados.TwittelumDatabase
import br.com.caelum.twittelumapp.R
import kotlinx.android.synthetic.main.activity_tweet.*
import modelo.Tweet
import viewmodel.TweetViewModel
import viewmodel.ViewModelFactory
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var localFoto: String? = null


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

            R.id.tweet_menu_foto -> {
                tiraFoto()
                return true
            }
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

    private fun tiraFoto() {
        val vaiPraCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        vaiPraCamera.putExtra(MediaStore.EXTRA_OUTPUT,caminhoFoto)
        startActivityForResult(vaiPraCamera,123)
    }

    fun defineLocalDaFoto(): Uri? {
        localFoto = "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"

        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this,"br.com.caelum.twittelumapp.fileprovider", arquivo)
    }


    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localFoto)
        val bm = Bitmap.createScaledBitmap(bitmap,450,300, true)
        tweet_foto.setImageBitmap(bm)
        tweet_foto.scaleType = ImageView.ScaleType.FIT_XY
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                carregaFoto()
            }
        }
    }
}
