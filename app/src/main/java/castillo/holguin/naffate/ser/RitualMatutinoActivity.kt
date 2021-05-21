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
    private lateinit var auth: FirebaseAuth
    private lateinit var usuario: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    lateinit var objAgua: String
    lateinit var objCorrer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        usuario = Firebase.auth
        storage = FirebaseFirestore.getInstance()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ritual_matutino)

        navegar.setOnClickListener {
            var intent: Intent = Intent(this, MenuOpciones::class.java)
            startActivity(intent)
        }

        btnGuardarObjetivos.setOnClickListener {
            agregarObjetivo()
        }

    }

    private fun agregarObjetivo() {
        val map = hashMapOf(
            "email" to usuario.currentUser?.email.toString(),
            "objetivoAgua" to txtCantidadAgua.text.toString(),
            "objetivoCorrer" to txtTiempoCorrer.text.toString())

        storage.collection("RitualMatutino")
            .add(map).addOnSuccessListener {
                Toast.makeText(getApplicationContext(),"Objetivo Agregado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(getApplicationContext(), "Intente de Nuevo", Toast.LENGTH_SHORT).show()
            }
    }

    private fun mostrarObjetivos(){
        storage.collection("RitualMatutino")
            .whereEqualTo("email",auth.currentUser?.email)
            .get()
            .addOnSuccessListener {
                it.forEach {
                    if(it.exists()){
                        objAgua = (it.getString("objetivoAgua").toString())
                        objCorrer = (it.getString("objetivoCorrer").toString())
                    }
                    txtCantidadAgua.setText(objAgua)
                    txtTiempoCorrer.setText(objCorrer)
                }
            }
    }
}
