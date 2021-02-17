package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydogapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dogs_rv_layout.view.*
import model.DogApi


open class DogAdapter( context: Context,private var dogImages: ArrayList<DogApi>, private var listener: onItemClickListener) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Picasso.get().load(dogImages[position].message).into(holder.itemView.dogImage)

    }

    override fun getItemCount(): Int {

        return dogImages.size
    }


   inner class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener {

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (listener!= null) {
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

        init {
            itemView.setOnClickListener(this)

        }

        val dogImages = itemView.dogImage!!

    }
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

}