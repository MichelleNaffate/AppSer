package castillo.holguin.naffate.ser

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            var user = FirebaseAuth.getInstance().currentUser?.email.toString()
            storage.collection("Usuarios").document("$user").set(
                hashMapOf(
                    "usuario" to "$usuarioNombre",
                    "email" to "$user")
            )
                .addOnSuccessListener {
                    Toast.makeText(this, "Cambio Exitoso $usuarioNombre", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(getApplicationContext(), "Intente de Nuevo", Toast.LENGTH_SHORT).show()
                }
        }

        navegar.setOnClickListener {
            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            var intent: Intent = Intent(this, UsuarioActivity::class.java)
            intent.putExtra("nombre", "$usuarioNombre")
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
                filePhat.putFile(url).addOnSuccessListener() {
                    nProgressDialog.dismiss()

                    val descargarFoto: String = nstorage.downloadUrl.toString()

                    // Glide.with(this).load(descargarFoto).fitCenter()
                    //       .centerCrop().into(perfil)
                    // val storageReference : FirebaseUser? = FirebaseAuth.getInstance().currentUser

// ImageView in your Activity
                    val imageView = findViewById<ImageView>(R.id.perfil)

// Download directly from StorageReference using Glide
// (See MyAppGlideModule for Loader registration)
                    Glide.with(this /* context */)
                        .load(descargarFoto).into(imageView)


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
        intent.putExtra("nombre", "$usuarioNombre")
        startActivity(intent)

    }

    @GlideModule
    class MyAppGlideModule : AppGlideModule() {
        fun registerComponents(context: Context?, glide: Glide?, registry: Registry) {
            // Register FirebaseImageLoader to handle StorageReference
            registry.append(StorageReference::class.java, InputStream::class.java,
                FirebaseImageLoader.Factory())
        }
    }
}