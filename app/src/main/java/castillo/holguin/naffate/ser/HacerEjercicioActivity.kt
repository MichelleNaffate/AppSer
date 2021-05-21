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
import kotlinx.android.synthetic.main.activity_hacer_ejercicio.*
import kotlinx.android.synthetic.main.activity_hacer_ejercicio.navegar
import kotlinx.android.synthetic.main.contenido.view.*
import kotlinx.android.synthetic.main.contenido.view.txtDetalle
import kotlinx.android.synthetic.main.contenido.view.txtTitulo

class HacerEjercicioActivity : AppCompatActivity() {

    var adapter: HacerEjercicioActivity.EjerciciosAdapter? = null
    var ejercicios = ArrayList<Contenido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hacer_ejercicio)
        cargarEjercicios()
        adapter = HacerEjercicioActivity.EjerciciosAdapter(ejercicios, this)
        gridviewHacerEjercicio.adapter = adapter
        navegar.setOnClickListener {
            var intent: Intent = Intent(this, CatalogoActividades::class.java)
            startActivity(intent)
        }
    }

    fun cargarEjercicios() {
        ejercicios.add(Contenido("7 Minutos de Ejercicio", "Probado cientificamente","7 min"))
        ejercicios.add(Contenido("3 minutos ¨Solo Muévete¨","Muévete, aunque sea por tres minutos","3 min"))
        ejercicios.add(Contenido("Corriendo la Semana 1","Empezar a ejercitarme","20 min"))
        ejercicios.add(Contenido("Corriendo la Semana 2","Creando un ritual","20 min"))
        ejercicios.add(Contenido("Corriendo la Semana 3","Transformando tu entorno","20 min"))
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
                    url = "https://www.youtube.com/watch?v=u1KxxgdPJEM"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 1){
                    url = "https://www.youtube.com/watch?v=5aAkskctYGY "
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 2){
                    url = "https://www.youtube.com/watch?v=dltZto6WkW8"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 3){
                    url = "https://www.youtube.com/watch?v=cywlBtOOWfY"
                    uri = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context!!.startActivity(intent)
                }
                else if(position == 4){
                    url = "https://www.youtube.com/watch?v=gxEIB1-dwco"
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