package xyz.farshad.vocab.component.Server

import com.google.common.base.Optional

import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.net.URL

/**
 * get request to api and convert response to string
 */
class RemoteGet : RemoteRequest() {

    /**
     *
     * @param link
     * @param data (in get request will be null)
     * @return JSONObject
     * @throws IOException
     * @throws URISyntaxException
     */
    @Throws(IOException::class, URISyntaxException::class)
    internal override fun send(link: String, data: Optional<Map<String, Any>>): JSONObject? {
        val url = URL(link)
        val client = DefaultHttpClient()
        val request = HttpGet()
        request.uri = URI(link)
        val response = client.execute(request)
        var res: JSONObject? = null
        try {
            res = getString(response)
        } catch (e: JSONException) {
            //e.printStackTrace();
        }

        return res

    }
}
