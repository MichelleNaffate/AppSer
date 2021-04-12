package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InicioSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)
        val button: Button = findViewById(R.id.btnAcceder)

        button.setOnClickListener {
            var intent : Intent = Intent(this, MenuOpciones:: class.java)
            startActivity(intent)


        }
    }
}