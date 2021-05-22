package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_inicio_sesion.*

class InicioSesionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        auth = Firebase.auth

        val btn_acceder: Button = findViewById(R.id.btnAcceder)

        btn_acceder.setOnClickListener {
            valida_ingreso()
        }

        view_olvidaContra.setOnClickListener {
            val intent: Intent = Intent(this, RecuperarPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun valida_ingreso(){
        val et_correo: EditText = findViewById(R.id.edit_Correo)
        val et_contra: EditText = findViewById(R.id.edit_contra)

        var correo: String = et_correo.text.toString()
        var contra: String = et_contra.text.toString()

        if(!correo.isNullOrBlank() && !contra.isNullOrBlank()){
            ingresarFirebase(correo,contra)
        }else{
            Toast.makeText(this, "Ingresar datos",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresarFirebase(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val intent:Intent = Intent(this,MenuOpciones::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onBackPressed() {
        super.onBackPressed()

        var intent: Intent = Intent(this, MenuMain::class.java)

        startActivity(intent)

    }

}