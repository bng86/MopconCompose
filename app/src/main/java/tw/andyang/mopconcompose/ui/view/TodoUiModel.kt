package tw.andyang.mopconcompose.ui.view

import tw.andyang.mopconcompose.model.Todo

sealed class TodoUiModel(val viewType: Int) {
    data class Header(val title: String) : TodoUiModel(VIEW_TYPE_TITLE)
    data class Item(val todo: Todo) : TodoUiModel(VIEW_TYPE_ITEM)

    companion object {
        const val VIEW_TYPE_TITLE = 0
        const val VIEW_TYPE_ITEM = 1
    }
}
