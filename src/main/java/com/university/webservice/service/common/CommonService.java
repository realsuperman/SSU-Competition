package com.university.webservice.service.common;

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
    public void deleteCommon(String userId,String typeCode) {
        commonRepository.deleteCommon(userId,typeCode);
    }

    @Transactional
    public void saveCommon(CommonSaveRequestDto dto){
        commonRepository.saveCommon(dto.toEntity().getUserId(),dto.toEntity().getTypeCode());
    }

}