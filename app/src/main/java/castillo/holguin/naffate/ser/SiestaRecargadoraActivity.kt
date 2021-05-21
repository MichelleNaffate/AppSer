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
import kotlinx.android.synthetic.main.activity_siesta_recargadora.*
import kotlinx.android.synthetic.main.activity_siesta_recargadora.navegar
import kotlinx.android.synthetic.main.contenido.view.*

class SiestaRecargadoraActivity : AppCompatActivity() {

    var adapter: SiestaRecargadoraActivity.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_siesta_recargadora)
        cargarEjercicios()
        adapter = SiestaRecargadoraActivity.EjerciciosAdapter(ejercicios, this)
        gridviewSiestaRecargadora.adapter = adapter
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, CatalogoActividades::class.java)
            startActivity(intent)
        }
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("Siesta de 10 Minutos con Sonidos Naturales", "Bosque salvaje y canciones de la naturaleza","10 min"))
        ejercicios.add(Contenido("Siesta Guiada con Toques Binaturales","30 minutos para relajar tu cuerpo","30 min"))
        ejercicios.add(Contenido("Siesta de 20 Minutos con Café","Antes de iniciar tomate un café en 5 min y disfuta la siesta con una gentil alarma al finalizar","20 min"))
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
                var url : String
                val uri: Uri
                if(position == 0){
                    url = "https://www.youtube.com/watch?v=RkEUxws0czA"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 1){
                    url = "https://www.youtube.com/watch?v=5kEA30hufAo"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 2){
                    url = "https://www.youtube.com/watch?v=w2VrrJ-Je64"
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