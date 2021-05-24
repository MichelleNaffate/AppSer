package castillo.holguin.naffate.ser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_habito_trabajar.*

class HabitoTrabajarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habito_trabajar)

        btnCompartir.setOnClickListener {
            var intent : Intent = Intent( android.content.Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_SUBJECT, "App Ser")
            intent.putExtra(Intent.EXTRA_TEXT, "¡Descarga ahora App Ser desde PlayStore!\n\nEmpieza donde estás.\nUsa lo que tienes.\nHaz lo que puedas.\nPor Artur Ashe.\n(Cita enviada desde App Ser).")
            startActivity(Intent.createChooser(intent, "Compartir vía"))
        }

        txtLeerMas.setOnClickListener {
            var url : String = "https://www.vogue.mx/belleza/bienestar/articulos/trucos-y-consejos-para-tomar-mas-agua-al-dia/12125"
            val uri: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        btnMetas.setOnClickListener {
            var intent : Intent = Intent(this, MetasActivity:: class.java)
            startActivity(intent)
        }

        navegar.setOnClickListener{
            var intent : Intent = Intent(this, MenuOpciones:: class.java)
            startActivity(intent)
        }

    }

}
