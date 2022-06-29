package com.geektech.lovecalculatore.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.geektech.lovecalculatore.data.entity.historymodel.HistoryModel;

import java.util.List;

@Dao
public interface LoveDao {

    @Query("SELECT * FROM `historymodel`")
    List<HistoryModel> getAllLoves();

    @Query("SELECT * FROM `historymodel` WHERE id IN (:id)")
    HistoryModel getCaseById(int id);

    @Query("SELECT * FROM `historymodel` ORDER BY firstName ASC")
    void getAllLovesByAlphabet();

    @Insert
    void addLove(HistoryModel taskModel);

    @Delete
    void deleteLove(HistoryModel taskModel);

    @Update
    void update(HistoryModel taskModel);

}
