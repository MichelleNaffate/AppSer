package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_recomendaciones.navegar
import kotlinx.android.synthetic.main.activity_ritual_matutino.*

class RitualMatutinoActivity : AppCompatActivity() {

    private lateinit var usuario: FirebaseAuth
    private lateinit var storage: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ritual_matutino)
        usuario = Firebase.auth
        storage = FirebaseFirestore.getInstance()

        var Bundle = intent.extras
        if (Bundle != null){
            var objAgua = Bundle.getString("objAgua")
            var objCorrer = Bundle.getString("objCorrer")
            txtCantidadAgua.setText("$objAgua")
            txtTiempoCorrer.setText("$objCorrer")
        }

        navegar.setOnClickListener {
            var intent: Intent = Intent(this, MenuOpciones::class.java)
            startActivity(intent)
        }

        btnGuardarObjetivos.setOnClickListener {
            agregarObjetivo()
        }

    }

    private fun agregarObjetivo() {
        var user = FirebaseAuth.getInstance().currentUser?.email.toString()
        storage.collection("RitualMatutino").document("$user")
            .set(hashMapOf(
                "objetivoAgua" to txtCantidadAgua.text.toString(),
                "objetivoCorrer" to txtTiempoCorrer.text.toString()))
            .addOnSuccessListener {
                Toast.makeText(getApplicationContext(),"Objetivo Agregado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(getApplicationContext(), "Intente de Nuevo", Toast.LENGTH_SHORT).show()
            }
    }
}

