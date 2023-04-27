package edu.mirea.hairloo1x3.sigma.data.data_sources.room.root;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao.TaskDAO;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;

@Database(entities = {TaskEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract TaskDAO taskDAO();
    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user_database").addCallback(RoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback RoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TaskDAO dao = INSTANCE.taskDAO();
                dao.deleteAll();

                TaskEntity task = new TaskEntity("Name", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("Функции", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("Графики", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("Числа", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("Производная по д ихрек", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("8 в степени 10 интеграл", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("найс эп броу", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("че происходит", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("ладно я пошел", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
                task = new TaskEntity("а я вообще ласт", "Description", "Section", "C", "Source", "H");
                dao.insert(task);
            });
        }
    };
}
