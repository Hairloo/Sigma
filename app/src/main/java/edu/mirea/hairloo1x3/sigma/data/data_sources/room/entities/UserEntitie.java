package edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "user_database")
public class UserEntitie {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String password;
    @ColumnInfo
    private String login;
    @ColumnInfo
    private List<Integer> idsFalse = new ArrayList<>();
    @ColumnInfo
    private List<Integer> idsCompleted = new ArrayList<>();
    @ColumnInfo
    private int points;
    public UserEntitie(){}
    public UserEntitie(int id, String email, String password, String login, int points) {
        this.id = id;
        idsFalse = new ArrayList<>();
        idsCompleted = new ArrayList<>();
        this.email = email;
        this.password = password;
        this.login = login;
        this.points = points;
    }
    public UserEntitie(int id) {
        this.id = id;
        points = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Integer> getIdsFalse() {
        return idsFalse;
    }

    public void setIdsFalse(List<Integer> idsFalse) {
        this.idsFalse = idsFalse;
    }

    public List<Integer> getIdsCompleted() {
        return idsCompleted;
    }

    public void setIdsCompleted(List<Integer> idsCompleted) {
        this.idsCompleted = idsCompleted;
    }
    public void addCompleted(int a){
        this.idsCompleted.add(a);
    }
    public void addFalse(int a){
        this.idsFalse.add(a);
    }
    public void delFalse(int a){
        idsFalse.remove(idsFalse.indexOf(a));
    }
    public void delCompleted(int a){
        idsCompleted.remove(idsCompleted.indexOf(a));
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
