package castillo.holguin.naffate.ser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_hacer_ejercicio.*
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
}