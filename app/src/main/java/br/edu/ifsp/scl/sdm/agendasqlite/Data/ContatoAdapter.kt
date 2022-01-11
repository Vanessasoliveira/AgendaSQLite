package br.edu.ifsp.scl.sdm.agendasqlite.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.agendasqlite.Model.Contato
import br.edu.ifsp.scl.sdm.agendasqlite.R

class ContatoAdapter(val contatosLista:ArrayList<Contato>): RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>() {

    var listener:ContatoListener?=null

    fun setClickListener(listener:ContatoListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContatoAdapter.ContatoViewHolder {
        //nome do layout que vai ser inflado
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contato_celula, parent, false)
        return ContatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoAdapter.ContatoViewHolder, position: Int) {
        holder.nomeVH.text = contatosLista[position].nome
        holder.foneVH.text = contatosLista[position].fone
        holder.emailVH.text =contatosLista[position].email
        holder.categoriaVH.text =contatosLista[position].categoria

    }

    override fun getItemCount(): Int {
        //retorna a quantidade de contatos da lista
        return contatosLista.size
    }

    inner class ContatoViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nomeVH = view.findViewById<TextView>(R.id.nome)
        val foneVH =view.findViewById<TextView>(R.id.fone)
        val emailVH =view.findViewById<TextView>(R.id.email)
        val categoriaVH =view.findViewById<TextView>(R.id.categoria)

        init{
            view.setOnClickListener{
                listener?.onItemClick(adapterPosition)
            }
        }

           }
    interface ContatoListener{
        fun onItemClick(pos: Int)
    }
}