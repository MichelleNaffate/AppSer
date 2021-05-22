package castillo.holguin.naffate.ser

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.*
import com.squareup.okhttp.internal.DiskLruCache
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_habito_trabajar.*
import kotlinx.android.synthetic.main.activity_habito_trabajar.navegar

class AjustesActivity : AppCompatActivity() {

    private lateinit var usuario: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var nstorage : StorageReference
    private var GALLERY_INTENT = 1
    private lateinit var nProgressDialog: ProgressDialog

    private lateinit var Usuarioss: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)
        nstorage =  FirebaseStorage.getInstance().reference
        usuario = Firebase.auth
        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        nProgressDialog = ProgressDialog(this)



        Usuarioss = intent.getStringExtra("Usuario").toString()

        var tv_nombre = findViewById(R.id.modificar_Usuario_nombre) as TextView
        var Bundle = intent.extras

        if (Bundle != null) {
            var nombre = Bundle.getString("nombre")


            tv_nombre.setText("$nombre")
        }
        btnEditar_foto.setOnClickListener(){
            var intent: Intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(intent, GALLERY_INTENT)
        }

        btnRecuperar_contraseña.setOnClickListener {
            var intent: Intent = Intent(this, RecuperarPasswordActivity::class.java)
            startActivity(intent)
        }

        btncambiarnombre_usuario.setOnClickListener() {

            storage.collection("Usuarios")
                .whereEqualTo("email", auth.currentUser?.email)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        if (it.exists()) {
                            if (Bundle != null) {
                                var nombre = Bundle.getString("nombre").toString()
                                val ref = storage.collection("Usuario").document()
                                val id = FieldPath.documentId()

                                var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
                                storage.collection("Usuarios").document("$nombre").delete()
                                val map = hashMapOf(
                                    "email" to auth.currentUser?.email,
                                    "Usuario" to "$usuarioNombre")
                                storage.collection("Usuarios").document("$usuarioNombre")
                                    .set(map)
                                    .addOnSuccessListener {
                                        Toast.makeText(this,"cambio exitoso $usuarioNombre", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener {  }

                            }
                        }
                    }
                }
        }
        navegar.setOnClickListener {
            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            var intent: Intent = Intent(this, UsuarioActivity::class.java)
            intent.putExtra("nombre","$usuarioNombre")
            startActivity(intent)

        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_INTENT && resultCode== RESULT_OK){

            nProgressDialog.setTitle("Subiendo.....")
            nProgressDialog.setMessage("Subiendo foto a perfil Ser")
            nProgressDialog.setCancelable(false)
            nProgressDialog.show()

            var url : Uri? = data?.data

            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            var filePhat:StorageReference = nstorage.child("$usuarioNombre").child(url?.lastPathSegment.toString())
            if (url != null) {
                filePhat.putFile(url).addOnSuccessListener {

                   nProgressDialog.dismiss()

                    ////var descargarFoto: Task<Uri> = nstorage.downloadUrl
                    //Glide.with(this).load(descargarFoto).fitCenter()
                      //      .centerCrop().into(perfil)


                    Toast.makeText(baseContext, "se ha cambiado correctamente.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
        var intent: Intent = Intent(this, UsuarioActivity::class.java)
        intent.putExtra("nombre","$usuarioNombre")
        startActivity(intent)

    }


    }

