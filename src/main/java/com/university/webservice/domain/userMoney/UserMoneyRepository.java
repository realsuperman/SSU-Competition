package com.university.webservice.domain.userMoney;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface UserMoneyRepository extends JpaRepository<UserMoney, Long> { // 이렇게하면 CRUD가 기본적으로 생김
    @Query("SELECT u FROM UserMoney u WHERE u.userId = ?1")
    Stream<UserMoney> findAllDesc(String userId);

    @Query("SELECT u FROM UserMoney u")
    Stream<UserMoney> findAllDesc();

    @Query(value="SELECT u.user_Id,u.year,u.month,u.money,u.regdate,u.moddate,u.money-sum(i.price) AS LEFT_MONEY FROM User_Money u LEFT JOIN User_Money_Item i ON u.user_Id = i.user_Id AND u.year = i.year AND u.month = i.month WHERE u.user_Id = ?1 GROUP BY u.user_Id,u.year,u.month,u.money,u.regdate,u.moddate",nativeQuery = true)
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
