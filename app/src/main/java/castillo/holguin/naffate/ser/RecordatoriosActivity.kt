package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recomendaciones.*
import kotlinx.android.synthetic.main.activity_recomendaciones.navegar
import kotlinx.android.synthetic.main.activity_recordatorios.*

class RecordatoriosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorios)
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, MenuOpciones::class.java)
            startActivity(intent)
        }

        btnAgregarNota.setOnClickListener{
            var intent:Intent = Intent(this,AgregarRecordatorio::class.java)
            startActivity(intent)
        }
    }
}