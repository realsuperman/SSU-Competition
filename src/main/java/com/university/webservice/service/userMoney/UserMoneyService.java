package com.university.webservice.service.userMoney;

import com.university.webservice.domain.users.UsersRepository;
import com.university.webservice.dto.users.UsersMainResponseDto;
import com.university.webservice.dto.users.UsersSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserMoneyService {
    private UsersRepository usersMoneyRepository;

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

}