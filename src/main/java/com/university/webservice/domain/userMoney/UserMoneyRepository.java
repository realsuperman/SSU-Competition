package com.university.webservice.domain.userMoney;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface UserMoneyRepository extends JpaRepository<UserMoney, Long> { // 이렇게하면 CRUD가 기본적으로 생김
    @Query("SELECT u FROM UserMoney u WHERE u.userId = ?1")
    Stream<UserMoney> findAllDesc(String userId);

    @Query("SELECT u FROM UserMoney u")
    Stream<UserMoney> findAllDesc();
}