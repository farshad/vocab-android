package xyz.farshad.vocab.component.Server

import com.google.common.base.Optional
import org.apache.http.HttpResponse
import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URISyntaxException

/**
 * this class handles get and post request to api
 */
abstract class RemoteRequest {

    /**
     * this abstract method shall implement by post and get request
     * data parameter will use in post request
     *
     * @param link
     * @param data
     * @return JSONObject
     * @throws IOException
     * @throws URISyntaxException
     */
    @Throws(IOException::class, URISyntaxException::class)
    internal abstract fun send(link: String, data: Optional<Map<String, Any>>): JSONObject

    /**
     * Convert response string to json object
     *
     * @param response
     * @return JSONObject
     * @throws JSONException
     * @throws IOException
     */
    @Throws(JSONException::class, IOException::class)
    fun getString(response: HttpResponse): JSONObject {

        val `in` = BufferedReader(InputStreamReader(response.entity.content))

        val sb = StringBuffer("")
        var line = ""

        while ((line = `in`.readLine()) != null) {
            sb.append(line)
            break
        }
        `in`.close()

        return JSONObject(sb.toString())
    }
}
