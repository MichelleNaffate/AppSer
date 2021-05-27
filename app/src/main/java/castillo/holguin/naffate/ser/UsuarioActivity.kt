package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_menu_opciones.*
import kotlinx.android.synthetic.main.activity_trabajo_sig_enfo.*
import kotlinx.android.synthetic.main.activity_usuario.*
import kotlinx.android.synthetic.main.activity_usuario.navegar

class UsuarioActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    lateinit var nombre: String
    private lateinit var nstorage: StorageReference
    private val TAG="FirebaseStorageManager"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        nstorage = FirebaseStorage.getInstance().reference
        cargarImagen()

        var tv_nombre = findViewById(R.id.nombre_Usuario) as TextView
        var Bundle = intent.extras
        if (Bundle != null) {
            var nombre = Bundle.getString("nombre")
            tv_nombre.setText("$nombre")
        }

        btnCerrarSesion.setOnClickListener() {
            auth.signOut()
            val intent: Intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btnAjustes.setOnClickListener() {

            val intent: Intent = Intent(this, AjustesActivity::class.java)
            intent.putExtra("nombre", tv_nombre.text.toString())
            startActivity(intent)
            finish()
        }

        navegar.setOnClickListener {
            var intent: Intent = Intent(this, MenuOpciones::class.java)
            startActivity(intent)
        }

        btnAyuda.setOnClickListener {
            var intent: Intent = Intent(this, ContenidoAyuda::class.java)
            startActivity(intent)

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent: Intent = Intent(this, MenuOpciones::class.java)
        startActivity(intent)

    }
    fun cargarImagen(){
        val imageFileName="${auth.currentUser?.email}/perfil.png"

        val dowloadURLTask= nstorage.child(imageFileName).downloadUrl
        dowloadURLTask.addOnSuccessListener {
            Log.e(TAG,"IMAGE PHAT $it")
            Glide.with(this /* context */)
                .load("$it").
                into(perfilUsuario)

        }

    }

}