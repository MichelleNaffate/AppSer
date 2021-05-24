package castillo.holguin.naffate.ser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_recomendaciones.navegar
import kotlinx.android.synthetic.main.activity_recordatorios.*
import kotlinx.android.synthetic.main.contenido_recordatorios.view.*

class RecordatoriosActivity : AppCompatActivity() {
    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth
    var adapter: RecordatoriosActivity.RecordatoriosAdapter? = null
    var recordatorio = ArrayList<Recordatorio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorios)

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()
        cargarRecordatorio()
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, MenuOpciones::class.java)
            startActivity(intent)
        }

        btnAgregarRecordatorio.setOnClickListener{
            var intent:Intent = Intent(this,AgregarRecordatorio::class.java)
            startActivity(intent)
        }
    }

    fun cargarRecordatorio(){
        storage.collection("Recordatorios")
            .whereEqualTo("email", usuario.currentUser?.email)
            .get()
            .addOnSuccessListener {
                it.forEach {
                    recordatorio!!.add(Recordatorio(it.getString("titulo")!!, it.getString("dia")!!,
                        it.getString("hora")!!))
                }
                adapter = RecordatoriosActivity.RecordatoriosAdapter(recordatorio,this)
                gridviewRecordatorio.adapter = adapter
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Error: Intente de Nuevo", Toast.LENGTH_SHORT).show()
            }
    }

    class RecordatoriosAdapter : BaseAdapter {
        var recordatorio = ArrayList<Recordatorio>()
        var context: Context? = null

        constructor(recordatorios: ArrayList<Recordatorio>, context: Context? ) : super(){
            this.recordatorio = recordatorios
            this.context = context
        }

        override fun getCount(): Int {
            return recordatorio.size
        }

        override fun getItem(p0: Int): Any {
            return recordatorio[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var recordatorio = recordatorio[p0]
            var inflador = LayoutInflater.from(this.context)
            var vista = inflador.inflate(R.layout.contenido_recordatorios, null)
            vista.txtTituloRecordatorio.setText(recordatorio.titulo)
            vista.txt_FechaRecordatorio.setText(recordatorio.dia)
            vista.txt_FechaRecordatorio.setText(recordatorio.dia)
            return vista
        }
    }
}