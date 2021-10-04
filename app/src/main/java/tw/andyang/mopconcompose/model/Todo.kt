package tw.andyang.mopconcompose.model

import androidx.room.*

@Entity(tableName = "todos")
data class Todo(
    @ColumnInfo(name = "checked") val checked: Boolean,
    @ColumnInfo(name = "text") val text: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)