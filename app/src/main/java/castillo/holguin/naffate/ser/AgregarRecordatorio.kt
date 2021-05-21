package castillo.holguin.naffate.ser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.zip.Inflater

class AgregarRecordatorio : AppCompatActivity() {

    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_recordatorio)

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()
    }
}