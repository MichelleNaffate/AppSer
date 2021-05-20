package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_habito_trabajar.*

class AjustesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        var tv_nombre = findViewById(R.id.modificar_Usuario_nombre) as TextView
        var Bundle = intent.extras

        if (Bundle != null){
            var nombre = Bundle.getString("nombre")


            tv_nombre.setText("$nombre")
        }
        navegar.setOnClickListener{
            var intent : Intent = Intent(this, UsuarioActivity:: class.java)
            startActivity(intent)
        }
    }

}