package com.university.webservice.domain.userMoney;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;

import java.util.stream.Stream;

public interface UserMoneyRepository extends JpaRepository<UserMoney, Long> { // 이렇게하면 CRUD가 기본적으로 생김
    @Query("SELECT u FROM UserMoney u WHERE u.userId = ?1")
    Stream<UserMoney> findAllDesc(String userId);

    @Query("SELECT u FROM UserMoney u")
    Stream<UserMoney> findAllDesc();

    @Query("SELECT u FROM UserMoney u WHERE u.userId = ?1")
    Stream<UserMoney> searchDesc(String userId);

    @Query("SELECT u FROM UserMoney u WHERE u.userId = ?1 AND u.year = ?2 AND u.month = ?3")
    Stream<UserMoney> searchDesc(String userId,String year,String month);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserMoney u WHERE u.userId=?1 AND u.year=?2 AND u.month=?3")
    void deleteView(String userId,String year, String month);

    @Modifying
    @Transactional
    @Query("UPDATE UserMoney u SET u.money=?2,u.moddate=CONCAT(CONCAT(CURRENT_DATE,' '),CURRENT_TIME) WHERE u.userId=?1 AND u.year=?3 AND u.month=?4")
    void updateView(String userId,Long money,String year,String month);

}
