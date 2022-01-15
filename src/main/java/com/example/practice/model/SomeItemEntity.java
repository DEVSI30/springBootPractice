package com.example.practice.model;

import com.example.practice.dto.SomeItemDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "SOME_ITEM_ENTITY")
public class SomeItemEntity {
    @Id
    private String id; // it 0000 0001

    private String title;
    private Long number1;
    private Long number2;
    private Boolean checkResult;
    private String content;

    public void update(SomeItemDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.number1 = dto.getNumber1();
        this.number2 = dto.getNumber2();
        this.checkResult = dto.getCheckResult();
    }

}
