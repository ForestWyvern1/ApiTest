package com.example.testapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.rv_view.view.*

class RvAdapter(val list: List<Genre>,  val context: Context) : RecyclerView.Adapter<RvAdapter.RVAdapter>(), RViewMovies.OnClickListener {

    override fun click(data: Result) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter {
        return RVAdapter(LayoutInflater.from(parent.context).inflate(R.layout.rv_view, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RVAdapter, position: Int) {

        holder.itemView.text_genre.text = list[position].name

        holder.itemView.rv_view_movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val disp = App.dm.api
            .getPopular()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({m ->
                var listPopular = mutableListOf<Result>()
                for (i in m.results){
                    for (j in list){
                        if (i.genre_ids.contains(j.id)){
                            listPopular.add(i)
                            break
                        }
                    }
                }
                holder.itemView.rv_view_movies.adapter = RViewMovies(listPopular, this, context) //onNext
            }, {
                Toast.makeText(context, "ВОВА ЛОХ", Toast.LENGTH_SHORT).show() //onError
            })
    }

    class RVAdapter(view: View) : RecyclerView.ViewHolder(view)


}