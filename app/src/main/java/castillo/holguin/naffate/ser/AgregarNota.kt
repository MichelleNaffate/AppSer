package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_agregar_nota.*
import kotlinx.android.synthetic.main.activity_agregar_nota.navegar
import kotlinx.android.synthetic.main.activity_habito_trabajar.*

class AgregarNota : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota)

        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        btnGuardarMeta.setOnClickListener(){
            val check: Boolean = false
            storage.collection("Metas").add(
                hashMapOf(
                    "check" to check,
                    "contenidoMeta" to edit_ContenidoMeta.text.toString(),
                    "email" to auth.currentUser?.email))
                .addOnSuccessListener {
                    Toast.makeText(getApplicationContext(),"Meta Agregada", Toast.LENGTH_SHORT).show()
                    var intent : Intent = Intent(this, MetasActivity:: class.java)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Toast.makeText(getApplicationContext(), "Intente de Nuevo", Toast.LENGTH_SHORT).show()
                }
        }

        navegar.setOnClickListener{
            var intent : Intent = Intent(this, MetasActivity:: class.java)
            startActivity(intent)
        }

    }
}