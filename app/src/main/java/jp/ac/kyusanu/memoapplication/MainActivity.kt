package jp.ac.kyusanu.memoapplication

import RecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var addList =ArrayList<MemoItem>()
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = recyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //初期データ
        val intro= listOf(
            MemoItem("初期データ1"),
            MemoItem("初期データ2"),
            MemoItem("初期データ3"),
        )
        for (i in intro) {
            addList.add(i)
        }

        //＋ボタン押したら
        binding.buttonAdd.setOnClickListener {
            //val addText : EditText = findViewById(R.id.addText)
            val data = MemoItem(binding.addText.text.toString())
            addList.add(data)
            recyclerAdapter.notifyItemInserted(addList.lastIndex)

            binding.addText.text = null
        }

        itemTouchHelper = ItemTouchHelper(getRecyclerViewSimpleCallBack())
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        val contentView = binding.root
        contentView.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = contentView.rootView.height - contentView.height
            if (heightDiff > 100) { // キーボードの高さが100ピクセル以上の場合
                binding.textBox.visibility = View.VISIBLE
            } else {
                binding.textBox.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
}