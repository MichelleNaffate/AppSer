package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuOpciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_opciones)
        val button: Button = findViewById(R.id.btnActividades)
        val button2: Button = findViewById(R.id.btnRecomendaciones)
        val button3: Button = findViewById(R.id.btnHabitoTrabajar)

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
    }
}