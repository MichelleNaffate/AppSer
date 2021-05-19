package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_menu_opciones.*
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    lateinit var nombre: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        storage = FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()

        regresarNombreUsuario()
        btnCerrarSesion.setOnClickListener(){
            val intent: Intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
        }


    }
    private fun regresarNombreUsuario(){
        storage.collection("Usuarios")
            .whereEqualTo("email",auth.currentUser?.email)
            .get()
            .addOnSuccessListener {
                it.forEach {
                    if(it.exists()){
                        nombre = it.getString("Usuario").toString()
                        nombre_Usuario.setText("$nombre")
                    }

                }
            }
    }
}