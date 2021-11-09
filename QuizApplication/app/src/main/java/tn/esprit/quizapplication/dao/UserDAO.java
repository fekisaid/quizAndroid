package tn.esprit.quizapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import tn.esprit.quizapplication.entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("Select * from user;")
    List<User> getAllUser();

    @Query("Select * from User where email=:email;")
    User getUserByEmail(String email);

    @Query("select * from user where id=:id;")
    User getUserById(String id);

    @Query("update user set password=:newpassword where email=:userEmail")
    void updatePasswordUserByEmail(String userEmail,String newpassword);

    @Query("select * from user where role='user'")
    List<User> getAllUserByRole();

    @Query("select * from user where role='user' and username LIKE :name")
    List<User> getAllUserByRoleAndName(String name);

    @Query("select count(*) from user where role='user'")
    int getNombreUser();



}
