package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

@Mapper
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

//    @Mapping(source = "filePath", target = "image")
    Ad adEntityToAd(AdEntity adEntity);

    List<Ad> adEntityListToAdList(List<AdEntity> adEntityList);
}
