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
import kotlinx.android.synthetic.main.activity_elongar.*
import kotlinx.android.synthetic.main.activity_respirar.*
import kotlinx.android.synthetic.main.activity_respirar.navegar
import kotlinx.android.synthetic.main.contenido.view.*

class RespirarActivity : AppCompatActivity() {

    var adapter: RespirarActivity.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_respirar)
        cargarEjercicios()
        adapter = RespirarActivity.EjerciciosAdapter(ejercicios, this)
        gridviewRespirar.adapter = adapter
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, CatalogoActividades::class.java)
            startActivity(intent)
        }
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("Respiración Energizante", "Como una taza de café, ¡pero solo toma 2 min!","2 min"))
        ejercicios.add(Contenido("Respiración Relajante","Un ejercicio de respiración para ayudar a relajarte","4 min"))
        ejercicios.add(Contenido("Medita con tu Respiración","Respira con tu respiración como un maestro Zen","11 min"))
    }

    class EjerciciosAdapter : BaseAdapter {
        var ejercicios = ArrayList<Contenido>()
        var context: Context? = null

        constructor(ejercicios: ArrayList<Contenido>, context: Context?) : super() {
            this.ejercicios = ejercicios
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var ejercicio = ejercicios[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.contenido, null)
            vista.txtTitulo.setText(ejercicio.titulo)
            vista.txtDetalle.setText(ejercicio.detalle)
            vista.txtMinutos.setText(ejercicio.minutos)
            vista.ContenidoActividad.setOnClickListener() {
                var url: String
                val uri: Uri
                if(position == 0){
                    url = "https://www.youtube.com/watch?v=QgXeoZzEq3o"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 1){
                    url = "https://www.youtube.com/watch?v=PhhYvS0RjzI"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 2){
                    url = "https://www.youtube.com/watch?v=yq1pn55YQR4"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
            }
            return vista
        }

        override fun getItem(position: Int): Any {
            return ejercicios[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return ejercicios.size
        }
    }
}