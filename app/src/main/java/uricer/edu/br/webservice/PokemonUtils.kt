package uricer.edu.br.webservice

import android.util.Log
import org.json.JSONObject

class PokemonUtils {

    fun getPokemon(url : String): Pokemon? {

        var json = NetworkUtils.getJSONFromAPI(url)

        return parseJson(json)

    }

    private fun parseJson(json: String): Pokemon? {
        val pokemon = Pokemon()
        //print(json)
        Log.i("Resultado", json)


        val jsonObj = JSONObject(json)

        val nome = jsonObj.getString("name")
        pokemon.nome = nome

        val id = jsonObj.getLong("id")
        pokemon.id = id.toString()

        val experiencia =
                jsonObj.getLong("base_experience")
        pokemon.experienciaBase = experiencia

        return pokemon
    }

}