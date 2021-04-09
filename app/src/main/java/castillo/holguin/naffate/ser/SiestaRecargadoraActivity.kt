package castillo.holguin.naffate.ser

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_siesta_recargadora.*
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
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("Siesta de 10 Minutos con Sonidos Naturales", "Bosque salvaje y canciones de la naturaleza","11 min"))
        ejercicios.add(Contenido("Siesta Guiada con Toques Binaturales","20 minutos para relajar tu cuerpo","20 min"))
        ejercicios.add(Contenido("Siesta de 25 Minutos con Café","Con una gentil alarma al finalizar","25 min"))
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