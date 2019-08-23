package com.university.webservice.service.userMoney;

import com.university.webservice.domain.userMoney.UserMoneyRepository;
import com.university.webservice.domain.users.UsersRepository;
import com.university.webservice.dto.common.CommonMainResponseDto;
import com.university.webservice.dto.common.CommonSaveRequestDto;
import com.university.webservice.dto.userMoney.UserMoneyMainResponseDto;
import com.university.webservice.dto.userMoney.UserMoneySaveRequestDto;
import com.university.webservice.dto.users.UsersMainResponseDto;
import com.university.webservice.dto.users.UsersSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UserMoneyService {
    private UsersRepository usersMoneyRepository;
    private UserMoneyRepository userMoneyRepository;

    @Transactional
    public Long save(UsersSaveRequestDto dto){
        return usersMoneyRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<UsersMainResponseDto> perfectLogin(String uId, String uPw) {
        return usersMoneyRepository.perfectLogin(uId, uPw)
                .map(UsersMainResponseDto::new)
                .collect(Collectors.toList());

    }
    @Transactional(readOnly = true)
    public List<UserMoneyMainResponseDto> searchDesc(String userId) {
/*        List<UserMoneyMainResponseDto> dto = new ArrayList<>();

        Stream<Object[]> results = userMoneyRepository.searchDesc(userId);
        results.forEach(r -> Arrays.toString(r));*/

        return userMoneyRepository.searchDesc(userId)
                .map(UserMoneyMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public void deleteView(String userId, String year, String month) {
        userMoneyRepository.deleteView(userId, year, month);
    }

    @Transactional(readOnly = true)
    public List<UsersMainResponseDto> findAllDesc(String userId) {
        return usersMoneyRepository.findAllDesc(userId)
                .map(UsersMainResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<UsersMainResponseDto> findAllDesc() {
        return usersMoneyRepository.findAllDesc()
                .map(UsersMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public String save(UserMoneySaveRequestDto dto){
        return userMoneyRepository.save(dto.toEntity()).getUserId();
    }


    @Transactional
    public void saveView(UserMoneySaveRequestDto dto) {
        UserMoneySaveRequestDto userMoneySaveRequestDto= new UserMoneySaveRequestDto(dto.toEntity().getUserId(),dto.toEntity().getYear(),dto.toEntity().getMonth(),dto.toEntity().getMoney());
        List<UserMoneyMainResponseDto> a = userMoneyRepository.searchDesc(userMoneySaveRequestDto.toEntity().getUserId(),userMoneySaveRequestDto.toEntity().getYear(),userMoneySaveRequestDto.toEntity().getMonth())
                .map(UserMoneyMainResponseDto::new)
                .collect(Collectors.toList());
        if(a.size() == 0){
            userMoneyRepository.save(userMoneySaveRequestDto.toEntity()).getUserId();
        }else{
            userMoneyRepository.updateView(userMoneySaveRequestDto.toEntity().getUserId(),dto.toEntity().getMoney(),dto.toEntity().getYear(),dto.toEntity().getMonth());
        }
    }

}