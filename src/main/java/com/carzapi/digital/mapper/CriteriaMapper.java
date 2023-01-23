package com.carzapi.digital.mapper;

import com.carzapi.digital.dao.entity.AnnouncementEntity;
import com.carzapi.digital.model.dto.LightAnnouncementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CriteriaMapper {
    CriteriaMapper INSTANCE = Mappers.getMapper(CriteriaMapper.class);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "modelName",source = "modelEntity.modelName")
    @Mapping(target = "price",source = "price")
    @Mapping(target = "motorSize",source = "motorSize")
    @Mapping(target = "distanceType",source = "distanceType")
    @Mapping(target = "distanceUnit",source = "distanceUnit")
    @Mapping(target = "year",source = "year")
    @Mapping(target = "privileges",source = "privileges")
    @Mapping(target = "createdAt", source = "createdAt")
    LightAnnouncementDto announcementToLightDto (AnnouncementEntity announcementEntity);

}
