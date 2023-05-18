package edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.checkerframework.common.aliasing.qual.Unique;

@Entity(tableName = "tasks_table")
public class TaskEntity {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo
    private String task_name;
    @ColumnInfo
    private String task_description;
    @ColumnInfo
    private String task_source;
    @ColumnInfo
    private String task_status = "N";
    @ColumnInfo
    private String task_section;
    @ColumnInfo
    private String task_dif;
    @ColumnInfo
    private String answer;
    public TaskEntity(){}
    public TaskEntity(int id, @NonNull String task_name, @NonNull String task_description, @NonNull String task_section, @NonNull String task_source, @NonNull String task_dif, @NonNull String answer){
        this.id = id;
        this.task_name = task_name;
        this.task_description = task_description;
        this.task_section = task_section;
        this.task_source = task_source;
        this.task_dif = task_dif;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getTask_source() {
        return task_source;
    }

    public void setTask_source(String task_source) {
        this.task_source = task_source;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getTask_section() {
        return task_section;
    }

    public void setTask_section(String task_section) {
        this.task_section = task_section;
    }

    public String getTask_dif() {
        return task_dif;
    }

    public void setTask_dif(String task_dif) {
        this.task_dif = task_dif;
    }
}
