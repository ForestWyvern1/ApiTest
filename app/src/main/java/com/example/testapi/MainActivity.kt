package com.example.testapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapi.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_view.view.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_view_for_api.layoutManager = LinearLayoutManager(this)


        val disp = App.dm.api
            .getPopular()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ m ->
                val disq = App.dm.api
                .getGenre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({n ->
                    var genre_list = mutableListOf<Genre>()
                    for (i in m.results) {
                        for (j in n.genres){
                            if (i.genre_ids.contains(j.id)){
                                genre_list.add(j)
                            }
                        }
                    }
                    rv_view_for_api.adapter = RvAdapter(genre_list.distinct(), this, applicationContext)
                }, {
                    Toast.makeText(this, "ВОВА ЛОХ", Toast.LENGTH_SHORT).show() //onError
                }, {

                })
            }, {
                Toast.makeText(this, "ВОВА ЛОХ", Toast.LENGTH_SHORT).show() //onError
            })
    }

}