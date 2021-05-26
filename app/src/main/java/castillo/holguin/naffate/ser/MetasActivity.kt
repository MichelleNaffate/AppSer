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
import kotlinx.android.synthetic.main.activity_hacer_ejercicio.*
import kotlinx.android.synthetic.main.activity_hacer_ejercicio.navegar
import kotlinx.android.synthetic.main.activity_metas.*
import kotlinx.android.synthetic.main.contenido_metas.*
import kotlinx.android.synthetic.main.contenido_metas.view.*

class MetasActivity : AppCompatActivity() {
    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth
    var adapter: MetasActivity.MetasAdapter? = null
    var meta = ArrayList<Meta>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metas)
        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()
        cargarMetas()
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, HabitoTrabajarActivity::class.java)
            startActivity(intent)
        }

        btnAgregarNota.setOnClickListener{
            var intent: Intent = Intent(this, AgregarNota::class.java)
            startActivity(intent)
        }

        /*
        cbxMetas.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked){
                checkMeta()
                buttonView.setTypeface(null, Typeface.ITALIC)
            }else{
                buttonView.setTypeface(null,Typeface.NORMAL)
            }
        }
        */

        btnGuardarCheck.setOnClickListener{
            checkMeta()
        }

    }

    fun cargarMetas(){
        storage.collection("Metas")
            .whereEqualTo("email", usuario.currentUser?.email)
            .get()
            .addOnSuccessListener {
                it.forEach {
                    meta!!.add(Meta(it.getString("contenidoMeta")!!, it.getBoolean("check")!!))
                }
                adapter = MetasActivity.MetasAdapter(meta,this)
                gridviewMetas.adapter = adapter
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Error: Intente de nuevo", Toast.LENGTH_SHORT).show()
            }
    }

    fun checkMeta(){
        if(cbxMetas.isChecked){
            var id: String = ""
            storage.collection("Metas")
                .whereEqualTo("check", false)
                .whereEqualTo("contenidoMeta", txtMeta.text.toString())
                .whereEqualTo("email", usuario.currentUser?.email)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        if (it.exists()) {
                            id = it.reference.id
                            storage.collection("Metas").document("$id").update("check", true)
                                .addOnSuccessListener {
                                    Toast.makeText(getApplicationContext(), "¡Felicidades meta cumplida!", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }.addOnFailureListener {
                    Toast.makeText(getApplicationContext(), "Intente de nuevo", Toast.LENGTH_SHORT).show()
                }
        }
    }


    class MetasAdapter : BaseAdapter {
        var metas = ArrayList<Meta>()
        var context: Context? = null

        constructor(metas: ArrayList<Meta>, context: Context? ) : super(){
            this.metas = metas
            this.context = context
        }

        override fun getCount(): Int {
            return metas.size
        }

        override fun getItem(p0: Int): Any {
            return metas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var meta = metas[p0]
            var inflador = LayoutInflater.from(this.context)
            var vista = inflador.inflate(R.layout.contenido_metas, null)
            vista.txtMeta.setText(meta.contenido)
            vista.cbxMetas.setChecked(meta.check)
            return vista
        }
    }
}