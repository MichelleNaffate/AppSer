package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_menu_opciones.*
import kotlinx.android.synthetic.main.activity_usuario.*

class MenuOpciones : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    lateinit var nombre: String
    lateinit var objAgua: String
    lateinit var objCorrer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_opciones)

        val button: Button = findViewById(R.id.btnActividades)
        val button2: Button = findViewById(R.id.btnRecomendaciones)
        val button3: Button = findViewById(R.id.btnHabitoTrabajar)
        val button4: Button = findViewById(R.id.btnRitualMatutino)
        val imagenUsuario: ImageView = findViewById(R.id.imageViewUsuario)
        storage = FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()

        button.setOnClickListener {
            var intent : Intent = Intent(this, CatalogoActividades:: class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            var intent : Intent = Intent(this, RecomendacionesActivity:: class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            var intent : Intent = Intent(this, HabitoTrabajarActivity:: class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            storage.collection("RitualMatutino")
                .whereEqualTo("email",auth.currentUser?.email)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        if (it.exists()) {
                            objAgua = (it.getString("objetivoAgua").toString())
                            objCorrer = (it.getString("objetivoCorrer").toString())
                            var intent: Intent = Intent(this, RitualMatutinoActivity::class.java)
                            intent.putExtra("objAgua", "$objAgua")
                            intent.putExtra("objCorrer", "$objCorrer")
                            startActivity(intent)
                        } else {
                            var intent: Intent = Intent(this, RitualMatutinoActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
        }

        imagenUsuario.setOnClickListener {
            storage.collection("Usuarios")
                .whereEqualTo("email",auth.currentUser?.email)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        if(it.exists()){
                            nombre = it.getString("Usuario").toString()
                            var intent : Intent = Intent(this, UsuarioActivity:: class.java)
                            intent.putExtra("nombre","$nombre")
                            startActivity(intent)
                            finish()
                        }
                    }
                }
        }

        btnRecordatorios.setOnClickListener {
            var intent : Intent = Intent(this, RecordatoriosActivity:: class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()

        System.exit(0)

    }
}


