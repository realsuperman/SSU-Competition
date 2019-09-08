package com.university.webservice.domain.userMoneyItem;
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

    @Query(value="SELECT u.user_Id,u.type_Code,c.type_Name,u.year,u.month,u.price,u.regdate,u.moddate,u.ratio FROM User_Money_Item u left join Common c on u.user_Id = c.user_Id AND u.type_Code = c.type_Code WHERE u.user_Id = ?1 AND u.year=?2 AND u.month=?3",nativeQuery = true)
    Stream<UserMoneyItem> itemView(String userId, String year, String month);
}
