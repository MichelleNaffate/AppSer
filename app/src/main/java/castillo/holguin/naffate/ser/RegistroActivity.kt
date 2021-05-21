package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_inicio_sesion.*
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        usuario = Firebase.auth
        storage = FirebaseFirestore.getInstance()

        val btn_registrar: Button = findViewById(R.id.btnRegistrarse)
        auth= FirebaseAuth.getInstance()

        btn_registrar.setOnClickListener {
            valida_registro()


        }
    }

    private fun valida_registro() {
        val et_correo: EditText = findViewById(R.id.edit_emailNuevo)
        val et_contra: EditText = findViewById(R.id.edit_contraNueva)
        val et_contraConf: EditText = findViewById(R.id.edit_contraConfNueva)
        // var et_nombUsuario: EditText = findViewById(R.id.edit_nombreUsuario)

        //var usuario: String = et_nombUsuario.text.toString()

        var correo: String = et_correo.text.toString()
        var contra1: String = et_contra.text.toString()
        var contra2: String = et_contraConf.text.toString()

        if (!correo.isNullOrBlank() && !contra1.isNullOrBlank() && !contra2.isNullOrBlank()) {
            if (contra1 == contra2) {
                registrarFirebase(correo, contra1)
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrarFirebase(correo: String, password: String) {
        usuario.createUserWithEmailAndPassword(correo, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = usuario.currentUser

                    var nombreUsuario = edit_nombreNuevo.text.toString()
                    var correo = edit_emailNuevo.text.toString()


                    val map = hashMapOf(
                        "email" to "$correo",
                        "Usuario" to "$nombreUsuario")
                    storage.collection("Usuarios").document("$nombreUsuario")
                        .set(map)
                        .addOnSuccessListener {
                            val intent: Intent = Intent(this, InicioSesionActivity::class.java)
                            startActivity(intent) }
                        .addOnFailureListener {  }

                    Toast.makeText(baseContext, "El usuario ${edit_nombreNuevo.text.toString()} se ha creado correctamente.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }
    }


