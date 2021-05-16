package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_menu_opciones.*
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        btnRecordatorios.setOnClickListener {
            var intent : Intent = Intent(this, RecordatoriosActivity:: class.java)
            startActivity(intent)
        }
        btnMetas.setOnClickListener {
            var intent : Intent = Intent(this, MetasActivity:: class.java)
            startActivity(intent)
        }
    }
}