package uricer.edu.br.webservice

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkUtils.trustEveryone()

        var banco = openOrCreateDatabase(
                "PokemonDB",
                Context.MODE_PRIVATE,
                null
        )

        banco.execSQL(
                """CREATE TABLE IF NOT EXISTS pokemon
                 (
                    id  VARCHAR(254) PRIMARY KEY,
                    nome VARCHAR(254),
                    experiencia VARCHAR(254)
                 );
                 """
        )

        banco.close()

        btn_criarPokemon.setOnClickListener {
            HttpService().execute(this)
        }

        btn_salvar.setOnClickListener(){
            var nome = text_nome.text
            var id = text_id.text
            var experiencia = text_experiencia.text

            var banco = openOrCreateDatabase(
                    "PokemonDB",
                    Context.MODE_PRIVATE,
                    null
            )

            banco.execSQL("""
                INSERT INTO pokemon ('id', 'nome', 'experiencia')
                SELECT '$id', '$nome', '$experiencia'
                WHERE NOT EXISTS(SELECT 1 FROM pokemon WHERE id = '$id');
            """)

            banco.close()
        }

        btn_tabela.setOnClickListener {
            val intent = Intent(this, TabelaPokemonActivity::class.java)
            startActivity(intent)
        }

    }
}
