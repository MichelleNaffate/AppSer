package castillo.holguin.naffate.ser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_recordatorios.*

class MostrarRecordatoriosActivity: AppCompatActivity() {

    private var adaptador: AdaptadorTareas? = null
    private lateinit var storage: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    companion object{
        var recordatorios1 = ArrayList<Recordatorios>()
        var first = true
    }

    fun onCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.activity_recordatorios, container, false)

        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        recordatorios1 = ArrayList()

        storage= FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()

        llenarRecordatorios()

        if(!recordatorios1.isEmpty()){
            var gridView: GridView = root.findViewById(R.id.gridviewRecordatorios)

            adaptador = AdaptadorTareas(root.context, recordatorios1)
            gridView.adapter = adaptador
        }
        return root
    }



    private fun llenarRecordatorios() {
        storage.collection("Recordatorios")
            .whereEqualTo("email",auth.currentUser?.email)
            .get()
            .addOnSuccessListener {
                it.forEach {
                    recordatorios1.add(Recordatorios(it.getString("titulo")!!, it.getString("dia")!! , it.getString("hora")!!))
                }
                adaptador = AdaptadorTareas(context!!, recordatorios1)
                gridviewRecordatorios.adapter = adaptador
            }
            .addOnFailureListener {
                Toast.makeText(baseContext, "Error: intente de nuevo", Toast.LENGTH_SHORT).show()
            }
    }

    private class AdaptadorTareas: BaseAdapter {
        var tareas = ArrayList<Recordatorios>()
        var context: Context? = null

        constructor(contexto: Context, tareas: ArrayList<Recordatorios>){
            this.context = contexto
            this.tareas = tareas
        }

        override fun getCount(): Int {
            return tareas.size
        }

        override fun getItem(p0: Int): Any {
            return tareas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var recordatorios2 = tareas[p0]
            var inflador = LayoutInflater.from(this.context)
            var vista = inflador.inflate(R.layout.contenido_recordatorios, null)

            var titulo: TextView = vista.findViewById(R.id.txtTituloRecor)
            var fecha: TextView = vista.findViewById(R.id.txt_Fecha)
            var tiempo: TextView = vista.findViewById(R.id.txt_Hora)

            titulo.setText(recordatorios2.titulo)
            fecha.setText(recordatorios2.dia)
            tiempo.setText(recordatorios2.time)

            return vista
        }
    }
}