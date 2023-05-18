package edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(UserEntitie user);

    @Update
    void updateUser(UserEntitie user);

    @Query("SELECT (SELECT COUNT(*) FROM user_database) != 0")
    boolean isUserExist();

    @Query("DELETE FROM user_database")
    void deleteAll();

    @Query("SELECT * FROM user_database WHERE id LIKE :id")
    UserEntitie findTaskById(int id);

    @Query("SELECT * FROM user_database")
    UserEntitie getUser();

}
