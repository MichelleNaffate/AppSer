package castillo.holguin.naffate.ser

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_agregar_nota.navegar
import kotlinx.android.synthetic.main.activity_agregar_recordatorio.*
import java.text.SimpleDateFormat
import java.util.*


class AgregarRecordatorio  : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_recordatorio)
        val root = layoutInflater

        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        edit_fechaRecordatorio.setOnClickListener{
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener{ datePicker, day, mounth, year ->
                cal.set(Calendar.DAY_OF_MONTH, day)
                cal.set(Calendar.MONTH, mounth)
                cal.set(Calendar.YEAR, year)
                edit_fechaRecordatorio.text.toString()

                var mes: Int = mounth + 1
                var fechaDisplay: String = "$year/$mes/$day"
                edit_fechaRecordatorio.setText(fechaDisplay)
            }
            DatePickerDialog(root.context, dateSetListener, cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)).show()
        }

        edit_HoraRecordatorio.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                var format = SimpleDateFormat("HH:mm").format(cal.time)
                edit_HoraRecordatorio.text.toString().format("$format")

                var horaDisplay: String = "$hour:$minute"
                edit_HoraRecordatorio.setText(horaDisplay)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()
        }

        btnGuardaRecordatorio.setOnClickListener(){
            storage.collection("Recordatorios").add(
                hashMapOf(
                    "titulo" to edit_tituloRecordatorio.text.toString(),
                    "dia" to edit_fechaRecordatorio.text.toString(),
                    "hora" to edit_HoraRecordatorio.text.toString(),
                    "email" to auth.currentUser?.email))
                .addOnSuccessListener {
                    Toast.makeText(getApplicationContext(),"Recordatorio Agregado", Toast.LENGTH_SHORT).show()
                    var intent : Intent = Intent(this, RecordatoriosActivity:: class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener{
                    Toast.makeText(getApplicationContext(), "Intente de Nuevo", Toast.LENGTH_SHORT).show()
                }
        }

        navegar.setOnClickListener{
            var intent : Intent = Intent(this, RecordatoriosActivity:: class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        var intent: Intent = Intent(this, RecordatoriosActivity::class.java)
        startActivity(intent)
        finish()

    }
}