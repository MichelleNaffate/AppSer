package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_usuario.*

class ContenidoAyuda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contenido_ayuda)
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, UsuarioActivity::class.java)
            startActivity(intent)
        }
    }
}