package castillo.holguin.naffate.ser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_Ser)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnContinuar)

        Toast.makeText(this,"bienvenido", Toast.LENGTH_SHORT).show()
        button.setOnClickListener {
            var intent : Intent = Intent(this, MenuMain:: class.java)
            startActivity(intent)
            finish()




        }
    }
}
