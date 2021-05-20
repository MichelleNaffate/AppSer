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
import kotlinx.android.synthetic.main.activity_trabajo_sig_enfo.*
import kotlinx.android.synthetic.main.activity_usuario.*
import kotlinx.android.synthetic.main.activity_usuario.navegar

class UsuarioActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    lateinit var nombre: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        storage = FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()
        var tv_nombre = findViewById(R.id.nombre_Usuario) as TextView
        var Bundle = intent.extras

        if (Bundle != null){
            var nombre = Bundle.getString("nombre")


            tv_nombre.setText("$nombre")
        }

        btnCerrarSesion.setOnClickListener(){
            val intent: Intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btnAjustes.setOnClickListener(){

            val intent: Intent = Intent(this, AjustesActivity::class.java)
            intent.putExtra("nombre",tv_nombre.text.toString())
            startActivity(intent)
        }
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, MenuOpciones::class.java)
            startActivity(intent)
        }
        btnAyuda.setOnClickListener {
            var intent: Intent = Intent(this, ContenidoAyuda::class.java)
            startActivity(intent)
        }

    }

}