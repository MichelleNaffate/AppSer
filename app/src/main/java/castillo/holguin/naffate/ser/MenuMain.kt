package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_menu_main.*

class MenuMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_main)

        btnIniciarSesion.setOnClickListener {
            var intent : Intent = Intent(this, InicioSesionActivity:: class.java)
            startActivity(intent)
            finish()
        }

        btnRediRegistrarse.setOnClickListener {
            var intent : Intent = Intent(this, RegistroActivity:: class.java)
            startActivity(intent)
            finish()
        }
    }
}
