package br.edu.ifsp.scl.sdm.agendasqlite.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.edu.ifsp.scl.sdm.agendasqlite.Model.Contato

class DatabaseHelper(context: Context):
    SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION ) {

    companion object {
        private val DATABASE_NAME = "agenda.db"
        private val DATABASE_VERSION = 1
        private val TABLE_NAME = "contatos"
        private val ID = "id"
        private val NOME = "nome"
        private val FONE = "fone"
        private val EMAIL = "email"
        private val CATEGORIA = "categoria"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NOME TEXT, $EMAIL TEXT, $FONE TEXT, $CATEGORIA TEXT)"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {


    }

    fun inseriContato(contato: Contato): Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, contato.id)
        values.put(NOME, contato.nome)
        values.put(FONE, contato.fone)
        values.put(EMAIL, contato.email)
        values.put(CATEGORIA, contato.categoria)
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result

    }

    fun atualizarContato(contato: Contato): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, contato.id)
        values.put(NOME, contato.nome)
        values.put(FONE, contato.fone)
        values.put(EMAIL, contato.email)
        values.put(CATEGORIA, contato.categoria)
        val result = db.update(TABLE_NAME, values, "id=?", arrayOf((contato.id.toString())))
        db.close()
        return result
    }

    fun apagarContato(contato: Contato): Int {
        val db = this.writableDatabase
        val result = db.delete(
            TABLE_NAME, "id=?", arrayOf(
                contato.id
                    .toString()
            )
        )
        db.close()
        return result
    }

    fun listarContatos():ArrayList<Contato>
    {
        val listaContatos = ArrayList<Contato>()
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $NOME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()){
            val c = Contato (cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4))
            listaContatos.add(c)
        }
        cursor.close()
        db.close()
        return listaContatos

    }



}