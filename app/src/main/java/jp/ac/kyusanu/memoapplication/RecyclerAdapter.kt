import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memoapplication.databinding.MemoItemBinding
import jp.ac.kyusanu.memoapplication.MemoItem

class RecyclerAdapter(private val memoList:ArrayList<MemoItem>) :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MemoItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item:MemoItem){
            binding.memoTextView.text = item.memo
            binding.memoEditText.setText(item.memo)
        }
        /*
        val memoContent  : TextView = v.findViewById(R.id.memoContent)
        private val memoEdit : EditText = v.findViewById(R.id.memoEdit)
        private val buttonEdit : Button = v.findViewById(R.id.buttonEdit)
         */

        //編集ボタン押したら編集モード
        init {
            binding.buttonEdit.setOnClickListener {
                if (binding.memoEditText.visibility == View.VISIBLE) {
                    // EditTextが表示されている場合、編集が完了したとみなしてTextViewに戻す
                    val editedText = binding.memoEditText.text.toString()
                    binding.memoTextView.text = editedText
                    binding.memoEditText.visibility = View.GONE
                    binding.memoTextView.visibility = View.VISIBLE
                } else {
                    // EditTextが非表示の場合、編集モードに切り替える
                    binding.memoEditText.setText(binding.memoTextView.text) // TextViewのテキストをEditTextに設定
                    binding.memoTextView.visibility = View.GONE
                    binding.memoEditText.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /*
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.memo_item,parent,false)
        return ViewHolderItem(view)
         */
        val binding = MemoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder = memoList[position]
        holder.bind(viewHolder)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}