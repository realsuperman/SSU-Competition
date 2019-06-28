package com.university.webservice.service.users;

import com.university.webservice.domain.users.UsersRepository;
import com.university.webservice.dto.users.UsersSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UsersService {
    private UsersRepository usersRepository;

    @Transactional
    public Long save(UsersSaveRequestDto dto){
        return usersRepository.save(dto.toEntity()).getId();
    }
}