package edu.mirea.hairloo1x3.sigma.data.data_sources.room.root;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao.TaskDAO;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.dao.UserDAO;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.Converter;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.TaskEntity;
import edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities.UserEntitie;
@TypeConverters(Converter.class)
@Database(entities = {UserEntitie.class}, version = 2, exportSchema = true)
public abstract class UserDatabase extends RoomDatabase {
    private static volatile UserDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract UserDAO userDAO();
    public static UserDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (UserDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
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
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
            });
        }
    };

}
