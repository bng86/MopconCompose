package tw.andyang.mopconcompose.ui.traditional

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import tw.andyang.mopconcompose.R
import tw.andyang.mopconcompose.ui.screen.TodoViewModel
import tw.andyang.mopconcompose.ui.view.TodoUiModel

class TodoListFragment : Fragment(R.layout.fragment_todo_list) {

    private val adapter = TodoAdapter()
    private val vm by viewModel<TodoViewModel>()

    var onAdd = {}
    var onEdit = { _: Int -> }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            onAdd.invoke()
        }

        adapter.onItemClick = { id ->
            onEdit.invoke(id)
        }

        adapter.onCheckChanged = { todo ->
            vm.editTodo(todo)
        }

        vm.fetchTodo().observe(viewLifecycleOwner) { todos ->
            val todoList = todos.filter { !it.checked }.map { TodoUiModel.Item(it) }
            val doneList = todos.filter { it.checked }.map { TodoUiModel.Item(it) }

            adapter.submitList(
                arrayListOf<TodoUiModel>().apply {
                    add(TodoUiModel.Header("Todo"))
                    addAll(todoList)
                    add(TodoUiModel.Header("Done"))
                    addAll(doneList)
                }
            )
        }
    }

    companion object {
        fun newInstance() = TodoListFragment()
    }
}