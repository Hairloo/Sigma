package edu.mirea.hairloo1x3.sigma.data.data_sources.room.entities;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.List;

public class Converter {
    @TypeConverter
    public String fromListToGson(List<Integer> list){
        return new Gson().toJson(list);
    }
    @TypeConverter
    public List<Integer> fromGsonToList(String list){
        return new Gson().fromJson(list, List.class);
    }
}
