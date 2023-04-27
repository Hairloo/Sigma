package edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class TaskEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task_name")
    private String taskName;
    private String taskDescription;
    private String taskSource;
    private String taskStatus;
    private String taskSection;
    private String taskDif;
    public TaskEntity(){}
    public TaskEntity(@NonNull String taskName, @NonNull String taskDescription, @NonNull String taskSection, @NonNull String taskStatus, @NonNull String taskSource, @NonNull String taskDif){
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskSection = taskSection;
        this.taskStatus = taskStatus;
        this.taskSource = taskSource;
        this.taskDif = taskDif;
    }
    @NonNull
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDif() {
        return taskDif;
    }

    public void setTaskDif(String taskDif) {
        this.taskDif = taskDif;
    }

    public String getTaskSource() {
        return taskSource;
    }

    public void setTaskSource(String taskSource) {
        this.taskSource = taskSource;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskSection() {
        return taskSection;
    }

    public void setTaskSection(String taskSection) {
        this.taskSection = taskSection;
    }
}
