import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.memoapplication.MemoData
import com.example.memoapplication.R

class RecyclerAdapter(private val memoList:ArrayList<MemoData>) :RecyclerView.Adapter<RecyclerAdapter.ViewHolderItem>() {

    inner class ViewHolderItem(v:View,rAdapter:RecyclerAdapter) :RecyclerView.ViewHolder(v) {
        val tvHolder : TextView = v.findViewById(R.id.tv)
        val editable_tvHolder : EditText = v.findViewById(R.id.editable_tv)
        val btnEdit : Button = v.findViewById(R.id.btnEdit)
        //編集ボタン押したら編集モード
        init {
            btnEdit.setOnClickListener {
                /*
                //編集ボタンが削除ボタンだったときの残骸
                val position: Int = adapterPosition
                val listPosition = memoList[position]

                AlertDialog.Builder(v.context)
                    .setTitle("${listPosition.mymemo}")
                    .setMessage("メモを削除しますか")

                    .setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                        memoList.removeAt(position)

                        rAdapter.notifyItemRemoved(position)
                    })
                    .setNegativeButton("Cancel", null)
                    .show()
            }

                 */
                if (editable_tvHolder.visibility == View.VISIBLE) {
                    // EditTextが表示されている場合、編集が完了したとみなしてTextViewに戻す
                    val editedText = editable_tvHolder.text.toString()
                    tvHolder.text = editedText
                    editable_tvHolder.visibility = View.GONE
                    tvHolder.visibility = View.VISIBLE
                    btnEdit.text = "編集" // ボタンのテキストを「編集」に変更
                } else {
                    // EditTextが非表示の場合、編集モードに切り替える
                    editable_tvHolder.setText(tvHolder.text) // TextViewのテキストをEditTextに設定
                    tvHolder.visibility = View.GONE
                    editable_tvHolder.visibility = View.VISIBLE
                    btnEdit.text = "完了" // ボタンのテキストを「完了」に変更
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val itemXml = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_memo,parent,false)
        return ViewHolderItem(itemXml,this)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val currentItem = memoList[position]
        holder.tvHolder.text = currentItem.mymemo
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}