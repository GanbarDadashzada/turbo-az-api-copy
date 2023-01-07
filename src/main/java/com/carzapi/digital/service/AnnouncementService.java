package com.carzapi.digital.service;

import com.carzapi.digital.dao.entity.AnnouncementEntity;
import com.carzapi.digital.dao.entity.BanTypeEntity;
import com.carzapi.digital.dao.entity.CityEntity;
import com.carzapi.digital.dao.entity.ColourEntity;
import com.carzapi.digital.dao.entity.ConditionEntity;
import com.carzapi.digital.dao.entity.EquipmentEntity;
import com.carzapi.digital.dao.entity.ModelEntity;
import com.carzapi.digital.dao.repo.AnnouncementRepo;
import com.carzapi.digital.dao.repo.BanTypeRepo;
import com.carzapi.digital.dao.repo.CityRepo;
import com.carzapi.digital.dao.repo.ColourRepo;
import com.carzapi.digital.dao.repo.ConditionRepo;
import com.carzapi.digital.dao.repo.EquipmentRepo;
import com.carzapi.digital.dao.repo.ModelRepo;
import com.carzapi.digital.mapper.AnnouncementMapper;
import com.carzapi.digital.model.dto.AnnouncementDto;
import com.carzapi.digital.model.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepo announcementRepo;
    private final ModelRepo modelRepo;
    private final BanTypeRepo banTypeRepo;
    private final ColourRepo colourRepo;
    private final CityRepo cityRepo;
    private final EquipmentRepo equipmentRepo;
    private final ConditionRepo conditionRepo;

    public AnnouncementDto createAnnouncement(AnnouncementDto announcementDto) {
        log.info("ActionLog.createAnnouncement.start");

        ModelEntity modelEntity = modelRepo.findById(announcementDto.getModelId()).orElseThrow(() -> {
                    log.error("ActionLog.createAnnouncement.error Model not found, modelId {}",
                            announcementDto.getModelId());
                    return new NotFoundException("Model not found");
                }
        );

        BanTypeEntity banTypeEntity = banTypeRepo.findById(announcementDto.getBanTypeId()).orElseThrow(() -> {
                    log.error("ActionLog.createAnnouncement.error Ban type not found, banTypeId {}",
                            announcementDto.getBanTypeId());
                    return new NotFoundException("Ban type not found");
                }
        );

        ColourEntity colourEntity = colourRepo.findById(announcementDto.getColourId()).orElseThrow(() -> {
                    log.error("ActionLog.createAnnouncement.error Colour not found, colourId {}",
                            announcementDto.getColourId());
                    return new NotFoundException("Colour not found");
                }
        );

        CityEntity cityEntity = cityRepo.findById(announcementDto.getCityId()).orElseThrow(() -> {
                    log.error("ActionLog.createAnnouncement.error City not found, cityId {}",
                            announcementDto.getCityId());
                    return new NotFoundException("City not found");
                }
        );

        AnnouncementEntity announcementEntity = announcementRepo.save(
                AnnouncementMapper.INSTANCE.buildAnnouncementEntity(
                        announcementDto,
                        modelEntity,
                        banTypeEntity,
                        colourEntity,
                        cityEntity,
                        equipmentRepo.findAllById(announcementDto.getEquipmentIds()),
                        conditionRepo.findAllById(announcementDto.getConditionIds())
                )
        );

        log.info("ActionLog.createAnnouncement.end");
        return AnnouncementMapper.INSTANCE.announcementEntityToDto(announcementEntity);
    }
}