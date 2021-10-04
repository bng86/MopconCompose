package tw.andyang.mopconcompose.ui.traditional

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tw.andyang.mopconcompose.R

class TraditionalMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TodoListFragment.newInstance().apply {
                onAdd = {
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.container, CreateTodoFragment.newInstance())
                        .commit()
                }
                onEdit = { id ->
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.container, EditTodoFragment.newInstance(id))
                        .commit()
                }
            })
            .commit()
    }
}