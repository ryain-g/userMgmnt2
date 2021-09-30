package com.hnbafrica.userMgmt.repository;

import com.hnbafrica.userMgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.firstName = :firstName")
    public User findUserByFirstName(@Param("firstName") String firstName);

    @Query("Update User u set  u.firstName = :firstName")
    public User updateUserByFirstName(@Param("firstName") String firstName);
}
