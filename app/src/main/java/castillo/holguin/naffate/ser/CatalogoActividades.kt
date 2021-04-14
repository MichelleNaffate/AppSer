package castillo.holguin.naffate.ser

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_actividades.view.*
import kotlinx.android.synthetic.main.activity_catalogo_actividades.*
import kotlinx.android.synthetic.main.activity_hacer_ejercicio.*
import kotlinx.android.synthetic.main.activity_siesta_recargadora.*
import kotlinx.android.synthetic.main.contenido.view.*
import kotlinx.android.synthetic.main.contenido.view.txtTitulo

class CatalogoActividades : AppCompatActivity() {

    var actividades = ArrayList<Actividades>()
    var adapter: ActividadesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_actividades)
        cargaractividades()
        adapter = ActividadesAdapter(actividades, this)
        gridActividades.adapter = adapter
    }
    fun cargaractividades() {
        actividades.add(Actividades("Hacer ejercicio", R.drawable.hacer_ejercicio))
        actividades.add(Actividades("Meditar", R.drawable.meditar))
        actividades.add(Actividades("Trabajo significativo \ny enfocado", R.drawable.trabajo))
        actividades.add(Actividades("Yoga", R.drawable.yoga))
        actividades.add(Actividades("Siesta \n recargadora", R.drawable.siesta))
        actividades.add(Actividades("Respira", R.drawable.respira))
        actividades.add(Actividades("Sientate y piensa", R.drawable.piensa))
        actividades.add(Actividades("Elongar", R.drawable.elongar))
         }

    class ActividadesAdapter : BaseAdapter {
        var actividades = ArrayList<Actividades>()
        var context: Context? = null

        constructor(actividades: ArrayList<Actividades>, context: Context?) : super() {
            this.actividades = actividades
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var actividades = actividades[position]
            var inflator = context!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.activity_actividades, null)
            vista.txtTitulo.setText(actividades.titulo)
            vista.imagen.setImageResource(actividades.imagen)
            vista.Actividad.setOnClickListener() {
                if (position== 0){
                    var intent = Intent(context, HacerEjercicioActivity::class.java)
                    context!!.startActivity(intent)
                }else if (position==1){
                    var intent = Intent(context, MeditarActivity::class.java)
                    context!!.startActivity(intent)
                }else if (position ==2){
                    var intent = Intent(context, TrabajoSigEnfoActivity::class.java)
                    context!!.startActivity(intent)
                }else if (position ==3){
                    var intent = Intent(context, YogaActivity::class.java)
                    context!!.startActivity(intent)
                }else if (position==4){
                    var intent = Intent(context, SiestaRecargadoraActivity::class.java)
                    context!!.startActivity(intent)
                }else if (position==5){
                    var intent = Intent(context, RespirarActivity::class.java)
                    context!!.startActivity(intent)
                }else if (position==6){
                    var intent = Intent(context, SientatePiensaActivity::class.java)
                    context!!.startActivity(intent)
                }else if (position==7){
                    var intent = Intent(context, ElongarActivity::class.java)
                    context!!.startActivity(intent)
                }
            }
            return vista
        }

        override fun getItem(position: Int): Any {
            return actividades[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return actividades.size
        }
    }
}


