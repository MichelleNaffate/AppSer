package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_habito_trabajar.*
import kotlinx.android.synthetic.main.activity_habito_trabajar.navegar

class AjustesActivity : AppCompatActivity() {

    private lateinit var usuario: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private lateinit var Usuarioss: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        usuario = Firebase.auth
        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()



        Usuarioss = intent.getStringExtra("Usuario").toString()

        var tv_nombre = findViewById(R.id.modificar_Usuario_nombre) as TextView
        var Bundle = intent.extras

        if (Bundle != null) {
            var nombre = Bundle.getString("nombre")


            tv_nombre.setText("$nombre")
        }


        btnRecuperar_contraseña.setOnClickListener {
            var intent: Intent = Intent(this, RecuperarPasswordActivity::class.java)
            startActivity(intent)
        }

        btncambiarnombre_usuario.setOnClickListener() {

            storage.collection("Usuarios")
                .whereEqualTo("email", auth.currentUser?.email)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        if (it.exists()) {
                            if (Bundle != null) {
                                var nombre = Bundle.getString("nombre").toString()
                                val ref = storage.collection("Usuario").document()
                                val id = FieldPath.documentId()

                                var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
                                storage.collection("Usuarios").document("$nombre").delete()
                                val map = hashMapOf(
                                    "email" to auth.currentUser?.email,
                                    "Usuario" to "$usuarioNombre")
                                storage.collection("Usuarios").document("$usuarioNombre")
                                    .set(map)
                                    .addOnSuccessListener {

                                        val intent: Intent = Intent(this, UsuarioActivity::class.java)
                                        intent.putExtra("nombre","$usuarioNombre")
                                        startActivity(intent)
                                        finish()


                                    }
                                    .addOnFailureListener {  }

                            }
                        }
                    }
                }
        }
        navegar.setOnClickListener {
            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            var intent: Intent = Intent(this, UsuarioActivity::class.java)
            intent.putExtra("nombre","$usuarioNombre")
            startActivity(intent)

        }

    }


    }

