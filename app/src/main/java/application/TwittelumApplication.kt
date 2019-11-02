package application

import android.app.Application

class TwittelumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instace = this
    }

    companion object {
        private lateinit var instace: TwittelumApplication

        fun getInstace() = instace
    }
}