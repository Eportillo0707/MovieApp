package tmdb.arch.movieapp.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tmdb.arch.movieapp.repository.local.model.FavoriteEntity
import tmdb.arch.movieapp.repository.local.model.ToWatchEntity

@Dao
internal interface MoviesDao {

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(entity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavoriteEntity)

    @Query("SELECT * FROM to_watch")
    fun getAllToWatch(): Flow<List<ToWatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToWatch(entity: ToWatchEntity)

    @Query ("DELETE FROM to_watch WHERE movie_id = :id")
    suspend fun deleteToWatch(id: Long)


}