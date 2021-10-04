package tw.andyang.mopconcompose.ui.traditional

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import tw.andyang.mopconcompose.R
import tw.andyang.mopconcompose.ui.screen.TodoViewModel

class CreateTodoFragment : Fragment(R.layout.fragment_create_todo) {

    private val vm by viewModel<TodoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText: EditText = view.findViewById(R.id.editText)

        view.findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            vm.addTodo(editText.text.toString())
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun newInstance() = CreateTodoFragment()
    }
}