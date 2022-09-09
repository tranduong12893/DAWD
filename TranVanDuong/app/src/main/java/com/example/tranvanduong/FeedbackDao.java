package com.example.tranvanduong;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FeedbackDao {
    @Insert
    void insertFeedback(FeedbackEntity feedback);

    @Query("SELECT * FROM Feedbacks")
    List<FeedbackEntity> getAllFeedback();
}
