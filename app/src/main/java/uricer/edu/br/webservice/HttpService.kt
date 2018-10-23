package uricer.edu.br.webservice

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import java.util.*

class HttpService : AsyncTask<AppCompatActivity, Void, Pokemon?>(){

    var activity : AppCompatActivity? = null

    override fun doInBackground(vararg activities: AppCompatActivity?): Pokemon? {

        this.activity = activities[0]

        var numero = Random().nextInt(802) + 1

        var url = "https://pokeapi.co/api/v2/pokemon/$numero/"

        var resposta = PokemonUtils().getPokemon(url)

        return resposta
    }

    override fun onPostExecute(result: Pokemon?) {
        super.onPostExecute(result)

        var activity = this.activity!!

        var nome = activity.findViewById<TextView>(R.id.text_nome)
        var id = activity.findViewById<TextView>(R.id.text_id)
        var experiencia = activity.findViewById<TextView>(R.id.text_experiencia)

        nome.text = result?.nome
        id.text = result?.id
        experiencia.text = result?.experienciaBase.toString()
    }



}