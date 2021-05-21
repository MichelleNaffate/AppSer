package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hacer_ejercicio.*

class MetasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metas)
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, HabitoTrabajarActivity::class.java)
            startActivity(intent)
        }
    }
}