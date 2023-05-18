package edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
@Dao
public interface TaskDAO {
    @Query("SELECT * FROM tasks_table ORDER BY task_name")
    LiveData<List<TaskEntity>> getAllTasks();

    @Query("SELECT * FROM tasks_table WHERE task_section='Algebra'")
    LiveData<List<TaskEntity>> getAlgebraTasks();

    @Query("SELECT * FROM tasks_table WHERE task_section='Matan'")
    LiveData<List<TaskEntity>> getMatanTasks();

    @Query("SELECT * FROM tasks_table WHERE task_section='Stats'")
    LiveData<List<TaskEntity>> getStatsTasks();

    @Query("SELECT * FROM tasks_table WHERE task_section='Logic'")
    LiveData<List<TaskEntity>> getLogicTasks();

    @Query("SELECT * FROM tasks_table WHERE task_section='Geometry'")
    LiveData<List<TaskEntity>> getGeometryTasks();

    @Query("SELECT * FROM tasks_table WHERE task_section='Combination'")
    LiveData<List<TaskEntity>> getCombinationTasks();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(TaskEntity task);

    @Query("DELETE FROM tasks_table")
    void deleteAll();

    @Update
    void updateTaskById(TaskEntity task);

    @Query("SELECT * FROM tasks_table WHERE id LIKE :id")
    TaskEntity findTaskById(int id);

}
