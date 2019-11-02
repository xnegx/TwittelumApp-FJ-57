package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import application.TwittelumApplication
import bancodedados.TweetRepository
import bancodedados.TwittelumDatabase
import bancodedados.TwittelumDatabase.Companion.getInstance

object ViewModelFactory : ViewModelProvider.Factory {
    private val database = TwittelumDatabase.getInstance(TwittelumApplication.getInstace())
    private val tweetRepository = TweetRepository(database.tweetDao())

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TweetViewModel(tweetRepository) as T
    }