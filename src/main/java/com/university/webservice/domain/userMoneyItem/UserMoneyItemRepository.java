package com.university.webservice.domain.userMoneyItem;

import com.university.webservice.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface UserMoneyItemRepository extends JpaRepository<UserMoneyItem, Long> { // 이렇게하면 CRUD가 기본적으로 생김
    @Query("SELECT u FROM Users u WHERE u.userId = ?1")
    Stream<UserMoneyItem> findAllDesc(String userId);

    @Query("SELECT u FROM Users u WHERE u.userId = ?1 AND u.userPw =?2")
    Stream<UserMoneyItem> perfectLogin(String uId, String uPw);

    @Query("SELECT u FROM Users u")
    Stream<UserMoneyItem> findAllDesc();
}