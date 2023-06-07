package jp.ac.kyusanu.memoapplication

import RecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memoapplication.R

class MainActivity : AppCompatActivity() {

    private var addList =ArrayList<MemoData>()
    private lateinit var recyclerView : RecyclerView
    private var recyclerAdapter = RecyclerAdapter(addList)

    private lateinit var itemTouchHelper : ItemTouchHelper

    private fun getRecyclerViewSimpleCallBack()=
        object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT) {
            //ドラッグしたら
            override fun onMove(

                p0: RecyclerView,
                p1: RecyclerView.ViewHolder,
                p2: RecyclerView.ViewHolder
            ): Boolean {

                val fromPosition = p1.bindingAdapterPosition
                val toPosition = p2.bindingAdapterPosition

                if (p1.itemViewType == p2.itemViewType) {
                    addList.add(toPosition, addList.removeAt(fromPosition))
                    recyclerAdapter.notifyItemMoved(fromPosition, toPosition)
                }

                return true
            }
            //スワイプしたら
            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                p0.let {
                    addList.removeAt(p0.bindingAdapterPosition)
                    recyclerAdapter.notifyItemRemoved(p0.bindingAdapterPosition)
                }
            }
        }
    //起動時
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        //初期データ
        val intro= listOf(
            MemoData("初期データ1"),
            MemoData("初期データ2"),
            MemoData("初期データ3"),
        )
        for (i in intro) {
            addList.add(i)
        }

        val btnAdd : Button = findViewById(R.id.btnAdd)
        //＋ボタン押したら
        btnAdd.setOnClickListener {
            val addText : EditText = findViewById(R.id.addText)
            val data = MemoData(addText.text.toString())
            addList.add(data)
            recyclerAdapter.notifyItemInserted(addList.lastIndex)

            addText.text = null
        }

        itemTouchHelper = ItemTouchHelper(getRecyclerViewSimpleCallBack())
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }
}