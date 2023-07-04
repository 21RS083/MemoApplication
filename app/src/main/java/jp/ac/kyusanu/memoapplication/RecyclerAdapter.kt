import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memoapplication.databinding.MemoItemBinding
import jp.ac.kyusanu.memoapplication.MemoItem

class RecyclerAdapter(private val memoList:ArrayList<MemoItem>) :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MemoItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item:MemoItem){
            //binding.memoTextView.text = item.memo
            binding.memoEditText.setText(item.memo)
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