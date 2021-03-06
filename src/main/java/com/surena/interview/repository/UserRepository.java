package com.surena.interview.repository;

import com.surena.interview.model.User;
import com.surena.interview.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);

    //    @Query("delete from User u where u.username= ?1")
    Long deleteByUsername(String username);

    @Modifying
    @Query("delete from User u where u.firstName=:firstName")
    void deleteWithFirstName(@Param("firstName") String firstName);
}
