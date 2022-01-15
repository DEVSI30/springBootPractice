package com.example.practice.dto;

import com.example.practice.model.SomeItemEntity;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SomeItemDTO {
    private String id; // it 0000 0001
    private String title;
    private String content;
    private Long number1;
    private Long number2;
    private Boolean checkResult;

    public static SomeItemEntity dtoToEntity(SomeItemDTO dto, String id) {
        return SomeItemEntity.builder()
                .id(id)
                .title(dto.getTitle())
                .content(dto.getContent())
                .number1(dto.getNumber1())
                .number2(dto.getNumber2())
                .checkResult(dto.getCheckResult()).build();
    }

    public static SomeItemDTO entityToDto(SomeItemEntity entity) {
        return SomeItemDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .number1(entity.getNumber1())
                .number2(entity.getNumber2())
                .checkResult(entity.getCheckResult()).build();
    }
}
