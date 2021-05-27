package castillo.holguin.naffate.ser

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseStorageManager {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore

    private val TAG="FirebaseStorageManager"
    private val mstorageRef= FirebaseStorage.getInstance().reference
    private lateinit var mProgressDialog: ProgressDialog


    fun uploadImage(mContext: Context, imageURI: Uri){
        auth = FirebaseAuth.getInstance()
        storage = FirebaseFirestore.getInstance()
        mProgressDialog= ProgressDialog(mContext)
        mProgressDialog.setMessage("Please wait, image being uploading...")
        val imageFileName="${auth.currentUser?.email}/perfil${System.currentTimeMillis()}.png"
        val UploadTask = mstorageRef.child("${auth.currentUser?.email}/perfil.png").putFile(imageURI)
        UploadTask.addOnSuccessListener{
            Log.e(TAG,"Image Upload Succesfuly")
            val dowloadURLTask= mstorageRef.child(imageFileName).downloadUrl
            dowloadURLTask.addOnSuccessListener {
                Log.e(TAG,"IMAGE PHAT $it")

                mProgressDialog.dismiss()
            }.addOnFailureListener{
                mProgressDialog.dismiss()
            }
        }.addOnFailureListener{
            Log.e(TAG,"Image upload failed ${it.printStackTrace()}")

        }
    }
}