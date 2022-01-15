package com.example.practice.service;

import com.example.practice.dto.ResponseDTO;
import com.example.practice.dto.SomeItemDTO;
import com.example.practice.model.QSomeItemEntity;
import com.example.practice.model.SomeItemEntity;
import com.example.practice.repository.SomeItemRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class SomeItemService {

    private final SomeItemRepository someItemRepository;
    private final EntityManager em;

    public String getNewId() {
        // id 생성 규칙 : IT + 0000 0001 (sequence)
        final String PREFIX = "it";

        QSomeItemEntity qSomeItem = QSomeItemEntity.someItemEntity;
        JPAQueryFactory qf = new JPAQueryFactory(em);

        String maxId = qf.select(qSomeItem.id.max()).from(qSomeItem).fetchOne();

        long numberMax = maxId == null ? 1L : Long.parseLong(maxId.substring(2)) + 1L;

        return String.format(PREFIX + "%08d", numberMax);
    }

    public ResponseDTO<SomeItemDTO> create(@RequestBody SomeItemDTO dto) {
        String newId = getNewId();
        SomeItemEntity savedItem = someItemRepository.save(SomeItemDTO.dtoToEntity(dto, newId));
        return ResponseDTO.<SomeItemDTO>builder().data(SomeItemDTO.entityToDto(savedItem)).build();
    }

    public ResponseDTO<SomeItemDTO> update(@RequestBody SomeItemDTO dto) {
        Optional<SomeItemEntity> byId = someItemRepository.findById(dto.getId());
        if (byId.isEmpty()) {
            return ResponseDTO.<SomeItemDTO>builder().error("No Entity").build();
        }

        SomeItemEntity original = byId.get();
        original.update(dto);

        someItemRepository.save(original);

        return ResponseDTO.<SomeItemDTO>builder().data(SomeItemDTO.entityToDto(original)).build();
    }

    public ResponseDTO<List<SomeItemDTO>> getList() {
        List<SomeItemEntity> all = someItemRepository.findAll();
        List<SomeItemDTO> someItemDTOList = all.stream().map(SomeItemDTO::entityToDto).collect(Collectors.toList());

        return ResponseDTO.<List<SomeItemDTO>>builder().data(someItemDTOList).build();
    }

    public ResponseDTO<SomeItemDTO> findById(String id) {
        Optional<SomeItemEntity> byId = someItemRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseDTO.<SomeItemDTO>builder().error("No Entity").build();
        }

        return ResponseDTO.<SomeItemDTO>builder().data(SomeItemDTO.entityToDto(byId.get())).build();
    }
}
