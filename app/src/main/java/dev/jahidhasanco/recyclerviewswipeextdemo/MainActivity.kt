package dev.jahidhasanco.recyclerviewswipeextdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.jahidhasanco.recyclerviewswipeext.SwipeListener
import dev.jahidhasanco.recyclerviewswipeext.makeLeftRightSwipeAble
import dev.jahidhasanco.recyclerviewswipeextdemo.data.model.ItemsViewModel
import dev.jahidhasanco.recyclerviewswipeextdemo.presentation.adapter.CustomAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.baseline_markunread_24, "Item $i"))
        }

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter

        recyclerview.makeLeftRightSwipeAble(this)
            .setLeftText("Delete")
            .setRightText("Archive")
            .setTextColor(R.color.white)
            .setListener(object : SwipeListener {

                override fun onSwipedLeft(position: Int) {
                    data.removeAt(position)
                    adapter.notifyDataSetChanged();
                    Snackbar.make(
                        recyclerview,
                        "Item $position Left",
                        Snackbar.LENGTH_LONG
                    ).show();
                }


                override fun onSwipedRight(position: Int) {
//                    data.removeAt(position)
                    adapter.notifyDataSetChanged();
                    Snackbar.make(
                        recyclerview,
                        "Item $position Right",
                        Snackbar.LENGTH_LONG
                    ).show();
                }

            })
            .createSwipeAble()

    }
}

