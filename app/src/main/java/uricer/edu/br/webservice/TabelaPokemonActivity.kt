package uricer.edu.br.webservice

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

import kotlinx.android.synthetic.main.tabela_pokemon.*

class TabelaPokemonActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabela_pokemon)

        btn_inicio.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        var banco = openOrCreateDatabase(
                "PokemonDB",
                Context.MODE_PRIVATE,
                null

        )

        var pokemons = findViewById<TableLayout>(R.id.tabela)

        pokemons.removeAllViews()

        var linhas = banco.rawQuery("SELECT * FROM pokemon ORDER BY nome ASC", null)

        while (linhas.moveToNext()) {
            var linha = TableRow(this)

            val id = TextView(this)
            val nome = TextView(this)
            val experiencia = TextView(this)

            id.gravity = Gravity.CENTER
            nome.gravity = Gravity.CENTER
            experiencia.gravity = Gravity.CENTER

            id.text = linhas.getString(0)
            nome.text = linhas.getString(1)
            experiencia.text = linhas.getString(2)

            linha.addView(id)
            linha.addView(nome)
            linha.addView(experiencia)

            pokemons.addView(linha)
        }

        banco.close()
    }

}
