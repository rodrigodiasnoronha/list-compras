package livrokotlin.com.br

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class ListaCompraDatabase(context: Context) : ManagedSQLiteOpenHelper(ctx = context, name = "listaCompras.db",
version = 1) {

    //singleton da classe
    companion object {
        private var instance: ListaCompraDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): ListaCompraDatabase {
            if(instance == null) {
                instance = ListaCompraDatabase(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override  fun onCriate(db: SQLiteDatabase) {
        // criação de tabelas

        db.createTable("produtos", true,
        "id" to INTEGER + PRIMARY_KEY + UNIQUE,
        "nome" to TEXT,
        "quantidade" to INTEGER,
        "valor" to REAL,
        "foto" to BLOB
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualização do banco de dados
    }
}

//Acesso à prioridade pelo contexto
val Context.database: ListaCompraDatabase
    get() = ListaCompraDatabase.getInstance(getApplicationContext())