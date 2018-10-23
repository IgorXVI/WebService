package uricer.edu.br.webservice

import uricer.edu.br.webservice.HttpUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


object NetworkUtils {

    //Responsavel por carregar o Objeto JSON
    fun getJSONFromAPI(url: String): String {
        var retorno = ""
        try {
            val apiEnd = URL(url)
            val codigoResposta: Int
            val conexao: HttpURLConnection
            val iss: InputStream

            conexao = apiEnd.openConnection() as HttpURLConnection
            conexao.setRequestMethod("GET")
            conexao.setReadTimeout(15000)
            conexao.setConnectTimeout(15000)
            conexao.connect()

            codigoResposta = conexao.getResponseCode()
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST) {
                iss = conexao.getInputStream()
            } else {
                iss = conexao.getErrorStream()
            }

            retorno = converterInputStreamToString(iss)
            iss.close()
            conexao.disconnect()

        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return retorno
    }

    private fun converterInputStreamToString(iss: InputStream): String {
        val buffer = StringBuffer()
        try {
            val br: BufferedReader
            var linha: String?

            br = BufferedReader(InputStreamReader(iss))
            while (true) {
                linha = br.readLine()
                if (linha == null) {
                    break;
                }
                buffer.append(linha)
            }

            br.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return buffer.toString()
    }

    fun trustEveryone() {
        HttpUtils.trustEveryone()
    }
}