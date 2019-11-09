package bancodedados

import modelo.Tweet

class TweetRepository(private val fonteDeDados: TweetDao) {

    fun lista() = fonteDeDados.lista()

    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)

    fun deleta(tweet: Tweet) = fonteDeDados.deleta(tweet)
}