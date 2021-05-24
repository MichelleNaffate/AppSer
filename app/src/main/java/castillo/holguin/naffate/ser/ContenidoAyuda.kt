package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_usuario.*
import kotlinx.android.synthetic.main.activity_usuario.navegar

class ContenidoAyuda : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    lateinit var nombre: String
    var user = FirebaseAuth.getInstance().currentUser?.email.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contenido_ayuda)
        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        navegar.setOnClickListener {
            storage.collection("Usuarios").document("$user")
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        nombre = (it.getString("usuario").toString())
                        var intent: Intent = Intent(this, UsuarioActivity::class.java)
                        intent.putExtra("nombre","$nombre")
                        startActivity(intent)
                    }
                }
        }
    }
}