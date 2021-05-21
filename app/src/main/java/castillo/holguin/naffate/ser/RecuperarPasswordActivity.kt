package castillo.holguin.naffate.ser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RecuperarPasswordActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_password)

        val btn_cambioContra: Button = findViewById(R.id.btnCambioContra)

        btn_cambioContra.setOnClickListener {
            val edit_recuperaContra: EditText = findViewById(R.id.edit_recuperaContra)

            val correo: String = edit_recuperaContra.text.toString()

            if(!correo.isNullOrBlank()){
                auth.sendPasswordResetEmail(correo).addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "se envio a $correo", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error al enviar correo", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Ingresar correo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}