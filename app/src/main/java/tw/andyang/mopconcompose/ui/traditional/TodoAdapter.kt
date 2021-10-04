package tw.andyang.mopconcompose.ui.traditional

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.andyang.mopconcompose.R
import tw.andyang.mopconcompose.model.Todo
import tw.andyang.mopconcompose.ui.view.TodoUiModel

class TodoAdapter : ListAdapter<TodoUiModel, RecyclerView.ViewHolder>(

    object : DiffUtil.ItemCallback<TodoUiModel>() {
        override fun areItemsTheSame(oldItem: TodoUiModel, newItem: TodoUiModel): Boolean {
            return oldItem.viewType == newItem.viewType
        }

        override fun areContentsTheSame(oldItem: TodoUiModel, newItem: TodoUiModel): Boolean {
            return oldItem == newItem
        }

    }
) {

    var onItemClick = { _: Int -> }
    var onCheckChanged = { _: Todo -> }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TodoUiModel.VIEW_TYPE_ITEM) {
            TodoViewHolder(parent)
        } else {
            TodoHeaderViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val todo = getItem(position)) {
            is TodoUiModel.Header -> {
                (holder as TodoHeaderViewHolder).bind(todo)
            }
            is TodoUiModel.Item -> {
                (holder as TodoViewHolder).let { it ->
                    it.bind(todo)
                    it.onItemClick = this.onItemClick
                    it.onCheckChanged = this.onCheckChanged
                }
            }
        }
    }
}

class TodoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
) {

    private val checkbox = itemView.findViewById<CheckBox>(R.id.checkbox)

    var onItemClick = { _: Int -> }
    var onCheckChanged = { _: Todo -> }

    fun bind(item: TodoUiModel.Item) {
        val todo = item.todo
        checkbox.isChecked = todo.checked
        checkbox.text = todo.text
        checkbox.paintFlags =
            if (todo.checked) checkbox.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG else 0
        checkbox.setOnCheckedChangeListener { view, checked ->
            if (view.isPressed) { // changed by user
                onCheckChanged.invoke(todo.copy(checked = checked))
            }
        }
        itemView.setOnClickListener {
            onItemClick.invoke(todo.id)
        }
    }
}

class TodoHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo_header, parent, false)
) {
    fun bind(item: TodoUiModel.Header) {
        (itemView as TextView).text = item.title
    }
}