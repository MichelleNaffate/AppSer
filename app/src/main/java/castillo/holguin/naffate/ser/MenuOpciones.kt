package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_menu_opciones.*
import kotlinx.android.synthetic.main.activity_usuario.*
import kotlinx.android.synthetic.main.activity_usuario.perfilUsuario

class MenuOpciones : AppCompatActivity() {

    private val TAG="FirebaseStorageManager"
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    var user = FirebaseAuth.getInstance().currentUser?.email.toString()
    lateinit var nombre: String
    lateinit var objAgua: String
    lateinit var objCorrer: String
    private lateinit var nstorage: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_opciones)

        storage = FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()
        nstorage = FirebaseStorage.getInstance().reference
        cargarImagen()

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
    fun cargarImagen(){
        val imageFileName="${auth.currentUser?.email}/perfil.png"

        val dowloadURLTask= nstorage.child(imageFileName).downloadUrl
        dowloadURLTask.addOnSuccessListener {
            Log.e(TAG,"IMAGE PHAT $it")
            Glide.with(this /* context */)
                .load("$it").
                into(btnUsuario)

        }

    }
}
