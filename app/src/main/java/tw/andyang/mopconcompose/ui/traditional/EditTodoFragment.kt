package tw.andyang.mopconcompose.ui.traditional

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import tw.andyang.mopconcompose.R
import tw.andyang.mopconcompose.ui.screen.TodoViewModel

class EditTodoFragment : Fragment(R.layout.fragment_edit_todo) {
    private val vm by viewModel<TodoViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(KEY_TODO_ID)
            ?: throw IllegalArgumentException("EDIT_TODO should need argument [id]")
        val editText: EditText = view.findViewById(R.id.editText)
        val updateButton: Button = view.findViewById(R.id.buttonUpdate)
        val deleteButton: Button = view.findViewById(R.id.buttonDelete)
        vm.findById(id).observe(viewLifecycleOwner) { todo ->
            updateButton.setOnClickListener {
                vm.editTodo(todo.copy(text = editText.text.toString()))
                parentFragmentManager.popBackStack()
            }
            deleteButton.setOnClickListener {
                vm.deleteTodo(todo)
                parentFragmentManager.popBackStack()
            }
            editText.setText(todo.text)
        }
    }
    companion object {
        private const val KEY_TODO_ID = "todo_id"
        fun newInstance(id: Int): EditTodoFragment {
            return EditTodoFragment().apply {
                arguments = bundleOf(KEY_TODO_ID to id)
            }
        }
    }
}