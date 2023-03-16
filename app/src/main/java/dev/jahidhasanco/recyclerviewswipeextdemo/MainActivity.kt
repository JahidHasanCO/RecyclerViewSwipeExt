package dev.jahidhasanco.recyclerviewswipeextdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.jahidhasanco.recyclerviewswipeextdemo.data.model.ItemsViewModel
import dev.jahidhasanco.recyclerviewswipeextdemo.lib.SwipeLeftRightCallback
import dev.jahidhasanco.recyclerviewswipeextdemo.lib.makeLeftRightSwipeAble
import dev.jahidhasanco.recyclerviewswipeextdemo.presentation.adapter.CustomAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_foreground, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        recyclerview.makeLeftRightSwipeAble(this)
            .setLeftText("Delete Left")
            .setRightText("Delete Right")
            .setLeftBg(R.color.purple_500)
            .setRightBg(R.color.purple_500)
            .setListener(object :SwipeLeftRightCallback.Listener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onSwipedLeft(position: Int) {
                    data.removeAt(position)
                    adapter.notifyDataSetChanged();
                    Snackbar.make(recyclerview,
                        "Item $position Marked As Read",
                        Snackbar.LENGTH_LONG).show();
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onSwipedRight(position: Int) {
                    data.removeAt(position)
                    adapter.notifyDataSetChanged();
                    Snackbar.make(recyclerview,
                        "Item $position Removed",
                        Snackbar.LENGTH_LONG).show();
                }

            }).createSwipeAble()

    }
}

