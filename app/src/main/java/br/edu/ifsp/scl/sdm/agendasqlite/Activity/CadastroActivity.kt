package br.edu.ifsp.scl.sdm.agendasqlite.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import br.edu.ifsp.scl.sdm.agendasqlite.Data.DatabaseHelper
import br.edu.ifsp.scl.sdm.agendasqlite.Model.Contato
import br.edu.ifsp.scl.sdm.agendasqlite.R

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cadastro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val db=DatabaseHelper(this)

        if (item.itemId==R.id.action_salvarContato){
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val fone = findViewById<EditText>(R.id.editTextFone).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val categoria = findViewById<EditText>(R.id.editTextCategoria).text.toString()

            val c = Contato(null, nome, fone, email, categoria)
            if(db.inseriContato(c)>0){
                Toast.makeText(this, "Contato inserido", Toast.LENGTH_LONG).show()
                finish()
            }

        }
        if(item.itemId==R.id.action_excluirContato){

        }

        return super.onOptionsItemSelected(item)
    }
}