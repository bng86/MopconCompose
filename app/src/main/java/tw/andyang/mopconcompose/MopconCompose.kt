package tw.andyang.mopconcompose

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import tw.andyang.mopconcompose.data.AppRepository
import tw.andyang.mopconcompose.ui.screen.TodoViewModel

class MopconCompose : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MopconCompose)
            modules(appModules())
        }
    }

    private fun appModules() = listOf(
        module {
            single { AppRepository(applicationContext) }
        },
        module {
            viewModel<TodoViewModel>()
        }
    )
}