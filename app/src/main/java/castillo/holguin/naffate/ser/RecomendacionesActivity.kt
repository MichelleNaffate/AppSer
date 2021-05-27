package castillo.holguin.naffate.ser

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_recomendaciones.*
import kotlinx.android.synthetic.main.activity_recomendaciones.navegar
import kotlinx.android.synthetic.main.contenido_recomendaciones.view.*

class RecomendacionesActivity : AppCompatActivity() {

    var adapter: RecomendacionesActivity.EjerciciosAdapter? = null
    var recomendaciones = ArrayList<ContenidoRecomendaciones>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones)
        cargarEjercicios()
        adapter = RecomendacionesActivity.EjerciciosAdapter(recomendaciones, this)
        gridviewRecomendaciones.adapter = adapter
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, MenuOpciones::class.java)
            startActivity(intent)
            finish()
        }


    }


    fun cargarEjercicios() {
        recomendaciones.add(ContenidoRecomendaciones("El arte de respirar", "Por Danny Penman",R.drawable.libro2))
        recomendaciones.add(ContenidoRecomendaciones("Un obsequio para el alma", "Por Enrique Villareal Aguilar",R.drawable.libro1))
        recomendaciones.add(ContenidoRecomendaciones("El fin de la ansiedad", "Por Gio Zararri",R.drawable.libro3))
    }

    class EjerciciosAdapter : BaseAdapter {
        var recomendacion = ArrayList<ContenidoRecomendaciones>()
        var context: Context? = null

        constructor(recomendaciones: ArrayList<ContenidoRecomendaciones>, context: Context?) : super() {
            this.recomendacion = recomendaciones
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var recomendacion = recomendacion[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.contenido_recomendaciones, null)
            vista.txtTitulo.setText(recomendacion.titulo)
            vista.txtDetalle.setText(recomendacion.autor)
            vista.imagenLibro.setImageResource(recomendacion.imagen)
            vista.ContenidoRecomendaciones.setOnClickListener(){
                var url : String
                val uri: Uri
                if(position == 0){
                    url = "https://www.amazon.com.mx/El-arte-respirar-guardado-mindfulness-ebook/dp/B06XDXRF9Z"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 1){
                    url = "https://www.amazon.com.mx/obsequio-para-el-alma/dp/6074571902"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 2){
                    url = "https://www.amazon.com.mx/El-Fin-Ansiedad-End-Anxiety/dp/8417664335"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
            }
            return vista
        }

        override fun getItem(position: Int): Any {
            return recomendacion[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return recomendacion.size
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        var intent: Intent = Intent(this, MenuOpciones::class.java)
        startActivity(intent)
        finish()

    }
}