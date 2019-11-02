package viewmodel

import androidx.lifecycle.ViewModel
import bancodedados.TweetRepository
import modelo.Tweet

class TweetViewModel(private val repository:TweetRepository): ViewModel() {

    fun lista() = repository.lista()

    fun salva(tweet: Tweet) = repository.salva(tweet)
}