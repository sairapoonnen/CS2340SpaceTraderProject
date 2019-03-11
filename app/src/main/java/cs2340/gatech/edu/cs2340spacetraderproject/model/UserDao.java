package cs2340.gatech.edu.cs2340spacetraderproject.model;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM User WHERE id = :id")
    User getUser(int id);

    @Delete
    void delete(User user);
}
