package tmdb.arch.movieapp.repository.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
internal class StringListConverter {

    private companion object {
        const val SEPARATOR = " , "
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? =
        list?.joinToString(separator = SEPARATOR)

    @TypeConverter
    fun toStringList(string: String?): List<String>? =
        string?.split(SEPARATOR)

}
