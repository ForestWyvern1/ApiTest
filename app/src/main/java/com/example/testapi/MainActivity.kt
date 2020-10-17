package com.example.testapi

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

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_view_for_api.layoutManager = LinearLayoutManager(this)

        val disp = App.dm.api
                .getGenre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({m ->
                    rv_view_for_api.adapter = RvAdapter(m.genres, this)
                    Toast.makeText(this, "ПРИШЛО, УРА!!!", Toast.LENGTH_SHORT).show() //onNext
                }, {
                    Toast.makeText(this, "ВОВА ЛОХ", Toast.LENGTH_SHORT).show() //onError
                }, {

                })
    }


}