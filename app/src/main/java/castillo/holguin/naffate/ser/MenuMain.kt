package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_main)
        val button: Button = findViewById(R.id.btnIniciarSesion)
        val button2: Button = findViewById(R.id.btnRediRegistrarse)

        button.setOnClickListener {
            var intent : Intent = Intent(this, InicioSesionActivity:: class.java)
            startActivity(intent)
            finish()


        }
        button2.setOnClickListener {
            var intent : Intent = Intent(this, RegistroActivity:: class.java)
            startActivity(intent)


        }
    }
}
