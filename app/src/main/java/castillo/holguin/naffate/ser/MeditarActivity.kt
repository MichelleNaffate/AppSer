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
import kotlinx.android.synthetic.main.activity_meditar.*
import kotlinx.android.synthetic.main.activity_meditar.navegar
import kotlinx.android.synthetic.main.contenido.view.*

class MeditarActivity : AppCompatActivity() {

    var adapter: MeditarActivity.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditar)
        cargarEjercicios()
        adapter = MeditarActivity.EjerciciosAdapter(ejercicios, this)
        gridviewMeditar.adapter = adapter
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, CatalogoActividades::class.java)
            startActivity(intent)
        }
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("Respiración I", "5 minutos de introducción a la meditación","5 min"))
        ejercicios.add(Contenido("Puerta a la Presencia","Estar presente","10 min"))
        ejercicios.add(Contenido("El Entrenamiento de la Meditación para Concentrarte","Enfocando tu mente","10 min"))
        ejercicios.add(Contenido("Meditación con Cuencos","Solo el sonido de cuencos","10 min"))
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
            vista.ContenidoActividad.setOnClickListener(){
                var url : String
                val uri: Uri
                if(position == 0){
                    url = "https://www.youtube.com/watch?v=TiJIrBR6hwQ"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 1){
                    url = "https://www.youtube.com/watch?v=lWnfAXXICwU"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 2){
                    url = "https://www.youtube.com/watch?v=Eur4geBBeuk"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 3){
                    url = "https://www.youtube.com/watch?v=GUIAE6FO8X0"
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