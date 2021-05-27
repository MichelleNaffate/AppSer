package castillo.holguin.naffate.ser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_contenido_ayuda.*
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
                        intent.putExtra("nombre", "$nombre")
                        startActivity(intent)
                        finish()
                    }
                }
        }

        btnEnviarDuda.setOnClickListener {
            storage.collection("Usuarios").document("$user")
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        nombre = (it.getString("usuario").toString())
                        var to: String = "app.ser.itson@gmail.com"
                        var subject: String = "Duda o comentario desde App Ser por $nombre"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + to))
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                        intent.putExtra(Intent.EXTRA_TEXT, edit_ContenidoDuda.text.toString())
                        startActivity(intent)

                    }
                }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        var intent: Intent = Intent(this, UsuarioActivity::class.java)
        startActivity(intent)
        finish()

    }
}