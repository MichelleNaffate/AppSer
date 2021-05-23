package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_menu_opciones.*
import kotlinx.android.synthetic.main.activity_usuario.*

class MenuOpciones : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    var user = FirebaseAuth.getInstance().currentUser?.email.toString()
    lateinit var nombre: String
    lateinit var objAgua: String
    lateinit var objCorrer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_opciones)

        storage = FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()

        btnActividades.setOnClickListener {
            var intent : Intent = Intent(this, CatalogoActividades:: class.java)
            startActivity(intent)
        }
        btnRecomendaciones.setOnClickListener {
            var intent : Intent = Intent(this, RecomendacionesActivity:: class.java)
            startActivity(intent)
        }

        btnHabitoTrabajar.setOnClickListener {
            var intent : Intent = Intent(this, HabitoTrabajarActivity:: class.java)
            startActivity(intent)
        }

        btnRitualMatutino.setOnClickListener {
            storage.collection("RitualMatutino").document("$user")
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        objAgua = (it.getString("objetivoAgua").toString())
                        objCorrer = (it.getString("objetivoCorrer").toString())
                        var intent: Intent = Intent(this, RitualMatutinoActivity::class.java)
                        intent.putExtra("objAgua", "$objAgua")
                        intent.putExtra("objCorrer", "$objCorrer")
                        startActivity(intent)
                    } else {
                        var intent: Intent = Intent(this, RitualMatutinoActivity::class.java)
                        startActivity(intent)
                    }
                }
        }

        btnUsuario.setOnClickListener {
            storage.collection("Usuarios").document("$user")
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        nombre = (it.getString("usuario").toString())
                        var intent : Intent = Intent(this, UsuarioActivity:: class.java)
                        intent.putExtra("nombre","$nombre")
                        startActivity(intent)
                    }
                }
        }

        btnRecordatorios.setOnClickListener {
            var intent : Intent = Intent(this, RecordatoriosActivity:: class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)
    }
}
