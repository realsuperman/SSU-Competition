package com.university.webservice.service.common;

import com.university.webservice.domain.common.CommonRepository;
import com.university.webservice.domain.common.CommonRepository;
import com.university.webservice.dto.common.CommonMainResponseDto;
import com.university.webservice.dto.common.CommonSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommonService {
    private CommonRepository commonRepository;

    @Transactional
    public String save(CommonSaveRequestDto dto){
        return commonRepository.save(dto.toEntity()).getUserId();
    }

    @Transactional(readOnly = true)
    public List<CommonMainResponseDto> findAllDesc(String userId) {
        return commonRepository.findAllDesc(userId)
                .map(CommonMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommonMainResponseDto> findAllDesc() {
        return commonRepository.findAllDesc()
                .map(CommonMainResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public void deleteCommon(String userId,Long typeCode) {
        commonRepository.deleteCommon(userId,typeCode);
    }


    @Transactional
    public void insertCommon(CommonSaveRequestDto dto) {
        Long seq = commonRepository.getCodeValue(dto.toEntity().getUserId());
        CommonSaveRequestDto commonSaveRequestDto= new CommonSaveRequestDto(dto.toEntity().getUserId(),seq,dto.toEntity().getTypeName());
        commonRepository.save(commonSaveRequestDto.toEntity()).getUserId();
    }

    @Transactional(readOnly = true)
    public void updateCommon(CommonSaveRequestDto dto) {
        commonRepository.updateCommon(dto.toEntity().getUserId(),dto.toEntity().getTypeCode(),dto.toEntity().getTypeName());
    }


}