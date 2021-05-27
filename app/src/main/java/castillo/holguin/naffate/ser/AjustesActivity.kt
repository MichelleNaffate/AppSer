package castillo.holguin.naffate.ser

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.Tasks
import com.google.api.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.*
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_habito_trabajar.*
import kotlinx.android.synthetic.main.activity_habito_trabajar.navegar
import java.io.InputStream
import java.util.*


class AjustesActivity : AppCompatActivity() {


    private lateinit var usuario: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var nstorage: StorageReference

    private var filePath: Uri? = null

    private lateinit var nProgressDialog: ProgressDialog
    private var PICK_IMAGE_REQUEST = 1234
    private lateinit var Usuarioss: String


    companion object {
        const val REQUEST_FROM_CAMERA = 1001
        const val REQUEST_FROM_GALLERY = 1002
    }

    private val TAG="FirebaseStorageManager"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)


        usuario = Firebase.auth
        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        nProgressDialog = ProgressDialog(this)
        Usuarioss = intent.getStringExtra("Usuario").toString()
        nstorage = FirebaseStorage.getInstance().reference
cargarImagen()
        var tv_nombre = findViewById(R.id.modificar_Usuario_nombre) as TextView
        var Bundle = intent.extras
        initUI()


        if (Bundle != null) {
            var nombre = Bundle.getString("nombre")
            tv_nombre.setText("$nombre")
        }



        btnRecuperar_contraseña.setOnClickListener {
            var intent: Intent = Intent(this, RecuperarPasswordActivity::class.java)
            startActivity(intent)
        }

        btncambiarnombre_usuario.setOnClickListener() {
            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            var user = FirebaseAuth.getInstance().currentUser?.email.toString()
            storage.collection("Usuarios").document("$user").set(
                hashMapOf(
                    "usuario" to "$usuarioNombre",
                    "email" to "$user"
                )
            )
                .addOnSuccessListener {
                    Toast.makeText(this, "Cambio Exitoso $usuarioNombre", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(getApplicationContext(), "Intente de Nuevo", Toast.LENGTH_SHORT)
                        .show()
                }
        }

        navegar.setOnClickListener {
            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            var intent: Intent = Intent(this, UsuarioActivity::class.java)
            intent.putExtra("nombre", "$usuarioNombre")
            startActivity(intent)
        }


    }

    private fun initUI() {
        btncamara.setOnClickListener {
            captureImageUsingCamera()
        }
        btnEditar_foto.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        ImagePicker.with(this).galleryOnly()
            .crop()
            .start(REQUEST_FROM_GALLERY)
    }

    private fun captureImageUsingCamera() {
        ImagePicker.with(this).cameraOnly()
            .crop()
            .start(REQUEST_FROM_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_FROM_CAMERA -> {
                    if (data != null) {
                        perfil.setImageURI(data.data)
                        FirebaseStorageManager().uploadImage(this, data.data!!)
                    }

                }
                REQUEST_FROM_GALLERY -> {
                    if (data != null) {

                        perfil.setImageURI(data.data)
                        FirebaseStorageManager().uploadImage(this, data.data!!)

                    }

                }
            }

            Toast.makeText(
                baseContext, "se ha cambiado correctamente.",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
        var intent: Intent = Intent(this, UsuarioActivity::class.java)
        intent.putExtra("nombre", "$usuarioNombre")
        startActivity(intent)

    }
    fun cargarImagen(){
        val imageFileName="${auth.currentUser?.email}/perfil.png"

        val dowloadURLTask= nstorage.child(imageFileName).downloadUrl
            dowloadURLTask.addOnSuccessListener {
                Log.e(TAG,"IMAGE PHAT $it")
                Glide.with(this /* context */)
                    .load("$it").
                    into(perfil)

            }

    }




}