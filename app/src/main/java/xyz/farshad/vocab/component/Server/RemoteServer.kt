package xyz.farshad.vocab.component.Server

import android.util.Log
import org.json.JSONObject

/**
 * this class is a facade for RemotePost and RemoteGet classes
 */
class RemoteServer {
    private val remoteGet: RemoteGet

    init {
        remoteGet = RemoteGet()
    }

    /**
     * call remoteGet class and send request
     *
     * @param link
     * @return JSONObject
     */
    operator fun get(link: String): Any? {

        try {
            return remoteGet.send(link, null)
        } catch (e: Exception) {
            return e
        }

    }
}
