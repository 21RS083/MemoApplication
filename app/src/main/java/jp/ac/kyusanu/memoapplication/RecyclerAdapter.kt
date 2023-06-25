import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jp.ac.kyusanu.memoapplication.MemoData
import com.example.memoapplication.R

class RecyclerAdapter(private val memoList:ArrayList<MemoData>) :RecyclerView.Adapter<RecyclerAdapter.ViewHolderItem>() {

    class ViewHolderItem(v:View) :RecyclerView.ViewHolder(v) {
        val memoContent  : TextView = v.findViewById(R.id.memoContent)//findViewByIdでビューにアクセスせず、View Bindingでアクセスする。
        private val memoEdit : EditText = v.findViewById(R.id.memoEdit)
        private val buttonEdit : Button = v.findViewById(R.id.buttonEdit)
        //編集ボタン押したら編集モード
        init {
            buttonEdit.setOnClickListener {
                if (memoEdit.visibility == View.VISIBLE) {
                    // EditTextが表示されている場合、編集が完了したとみなしてTextViewに戻す
                    val editedText = memoEdit.text.toString()
                    memoContent.text = editedText
                    memoEdit.visibility = View.GONE
                    memoContent.visibility = View.VISIBLE
                    buttonEdit.text = "編集" // ボタンのテキストを「編集」に変更
                } else {
                    // EditTextが非表示の場合、編集モードに切り替える
                    memoEdit.setText(memoContent.text) // TextViewのテキストをEditTextに設定
                    memoContent.visibility = View.GONE
                    memoEdit.visibility = View.VISIBLE
                    buttonEdit.text = "完了" // ボタンのテキストを「完了」に変更
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.memo_item,parent,false)
        return ViewHolderItem(view)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val viewHolder = memoList[position]
        holder.memoContent.text = viewHolder.memo
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}