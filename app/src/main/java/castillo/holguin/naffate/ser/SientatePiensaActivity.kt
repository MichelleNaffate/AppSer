package castillo.holguin.naffate.ser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_elongar.*
import kotlinx.android.synthetic.main.activity_sientate_piensa.*
import kotlinx.android.synthetic.main.activity_sientate_piensa.navegar
import kotlinx.android.synthetic.main.contenido.view.*

class SientatePiensaActivity : AppCompatActivity() {

    var adapter: SientatePiensaActivity.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sientate_piensa)
        cargarEjercicios()
        adapter = SientatePiensaActivity.EjerciciosAdapter(ejercicios, this)
        gridviewSientatePiensa.adapter = adapter
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, CatalogoActividades::class.java)
            startActivity(intent)
        }
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("Moldea tu Mente", "Reprograma tu realidad","5 min"))
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