package castillo.holguin.naffate.ser

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class AgregarRecordatorio  : AppCompatActivity() {

    private lateinit var storage: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_recordatorio)

        val root = inflater.inflate(R.layout.activity_agregar_recordatorio, container, false)

        storage= FirebaseFirestore.getInstance()
        auth= FirebaseAuth.getInstance()

        val btn_time: Button = root.findViewById(R.id.btnHoraRecordatorio)

        btn_time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()
        }

        val edit_titulo = root.findViewById(R.id.edit_tituloRecordatorio) as EditText
        val edit_fecha = root.findViewById(R.id.edit_fecha) as EditText
        val btn_guardar = root.findViewById(R.id.btnGuardaRecordatorio) as Button

        btn_guardar.setOnClickListener {

            var titulo = edit_titulo.text.toString()
            var dia = edit_fecha.text.toString()
            var time = btn_time.text.toString()

            val recordatorio1 = hashMapOf(
                "titulo" to edit_titulo.text.toString(),
                "dia" to edit_fecha.text.toString(),
                "hora" to btn_time.text.toString())

            storage.collection("Recordatorios")
                .add(recordatorio1)
                .addOnSuccessListener {
                    Toast.makeText(root.context, "Task Agregada", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(root.context, "Error: Intente de nuevo", Toast.LENGTH_SHORT).show()
                }
        }
        return root
    }
}