package castillo.holguin.naffate.ser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_siesta_recargadora.*
import kotlinx.android.synthetic.main.contenido.view.*

class respirar : AppCompatActivity(){

    var adapter: respirar.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.respirar)
        cargarEjercicios()
        adapter = respirar.EjerciciosAdapter(ejercicios, this)
        gridviewSiestaRecargadora.adapter = adapter
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("Respiración Energizante", "Como una taza de café, ¡Pero solo te toma 30 segundos!","1 min"))
        ejercicios.add(Contenido("Respiración Relajante","Un ejercicio de respiración para ayudar a relajarte","1 min"))
        ejercicios.add(Contenido("Medita con tu Respiración","Respira con tu respiración como un Maestro Zen","1 min"))
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