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
import kotlinx.android.synthetic.main.contenido.view.*

class ElongarActivity : AppCompatActivity() {

    var adapter: ElongarActivity.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elongar)
        cargarEjercicios()
        adapter = ElongarActivity.EjerciciosAdapter(ejercicios, this)
        gridviewElongar.adapter = adapter
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, CatalogoActividades::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("10 Minutos para Empezar a Estirarte", "Flexibilidad diaria","10 min"))
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
                if(position == 0) {
                    url = "https://www.youtube.com/watch?v=KrgcBtBe5KI"
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
    override fun onBackPressed() {
        super.onBackPressed()
        var intent: Intent = Intent(this, CatalogoActividades::class.java)
        startActivity(intent)
        finish()

    }
}