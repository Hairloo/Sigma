package edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
@Dao
public interface TaskDAO {
    @Query("SELECT * FROM tasks_table ORDER BY task_name")
     LiveData<List<TaskEntity>> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TaskEntity task);

    @Query("DELETE FROM tasks_table")
    void deleteAll();
}
