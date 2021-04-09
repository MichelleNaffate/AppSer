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
import kotlinx.android.synthetic.main.contenido.view.*

class HacerEjercicioActivity : AppCompatActivity() {

    var adapter: HacerEjercicioActivity.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hacer_ejercicio)
        cargarEjercicios()
        adapter = HacerEjercicioActivity.EjerciciosAdapter(ejercicios, this)
        gridviewHacerEjercicio.adapter = adapter
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("7 Minutos de Ejercicio", "Probado cientificamente","9 min"))
        ejercicios.add(Contenido("1 minuto ¨Solo Muévete¨","Muévete, aunque sea por un minuto","1 min"))
        ejercicios.add(Contenido("Corriendo la Semana 1","Empezar a ejercitarme","15 min"))
        ejercicios.add(Contenido("Corriendo la Semana 2","Creando un ritual","22 min"))
        ejercicios.add(Contenido("Corriendo la Semana 3","Transformando tu entorno","22 min"))

    }

    class EjerciciosAdapter : BaseAdapter {
        var ejercicios = ArrayList<Contenido>()
        var context: Context? = null

        constructor(ejercicios: ArrayList<Contenido>, context2: Context?) : super() {
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