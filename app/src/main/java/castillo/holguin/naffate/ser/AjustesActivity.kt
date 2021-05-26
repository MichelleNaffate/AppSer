package castillo.holguin.naffate.ser

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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


class AjustesActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var usuario: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var nstorage : StorageReference
    private var GALLERY_INTENT = 1
    private var filePath: Uri? = null

    private lateinit var nProgressDialog: ProgressDialog
    private var PICK_IMAGE_REQUEST=1234
    private lateinit var Usuarioss: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        usuario = Firebase.auth
        storage = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        nProgressDialog = ProgressDialog(this)
        Usuarioss = intent.getStringExtra("Usuario").toString()
        nstorage = FirebaseStorage.getInstance().reference

        var tv_nombre = findViewById(R.id.modificar_Usuario_nombre) as TextView
        var Bundle = intent.extras
        btnEditar_foto.setOnClickListener(this)
        btnguardar.setOnClickListener(this)

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


            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
                filePath = data.data
                nProgressDialog.setTitle("Subiendo.....")
                nProgressDialog.setMessage("Subiendo foto a perfil Ser")
                nProgressDialog.setCancelable(false)
                nProgressDialog.show()

                try {
                    nProgressDialog.dismiss()

                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                    perfil.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()

                }
                var usuarioNombre: String = modificar_Usuario_nombre.text.toString()

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

    @GlideModule
    class MyAppGlideModule : AppGlideModule() {
        fun registerComponents(context: Context?, glide: Glide?, registry: Registry) {
            // Register FirebaseImageLoader to handle StorageReference
            registry.append(StorageReference::class.java, InputStream::class.java,
                FirebaseImageLoader.Factory())
        }
    }

    override fun onClick(v: View?) {

        if(v==btnEditar_foto){

            showFileChooser()


        }else{
            UploadFile()

        }
    }

    private fun UploadFile() {
        if(filePath!=null){
            val progressDialog= ProgressDialog(this)
            progressDialog.setTitle("cargando....")
            progressDialog.show()
            var usuarioNombre: String = modificar_Usuario_nombre.text.toString()
            val imageref = nstorage!!.child("$usuarioNombre/"+ UUID.randomUUID().toString())
            imageref.putFile(filePath!!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext,"$imageref File Upload", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener(){
                progressDialog.dismiss()
                Toast.makeText(applicationContext,"Failed", Toast.LENGTH_SHORT).show()

            }.addOnProgressListener { taskSnapshot ->
                val progress = 100.0*taskSnapshot.bytesTransferred/taskSnapshot.totalByteCount
                progressDialog.setMessage("Upload"+progress.toInt()+"%...")
            }

        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type="image/"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"SELECCIONE UNA IMAGEN"),PICK_IMAGE_REQUEST)
    }
}