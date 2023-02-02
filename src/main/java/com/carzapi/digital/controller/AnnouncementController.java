package com.carzapi.digital.controller;

import com.carzapi.digital.model.dto.AnnouncementCriteria;
import com.carzapi.digital.model.dto.AnnouncementDto;
import com.carzapi.digital.model.dto.AnnouncementRequest;
import com.carzapi.digital.model.dto.LightAnnouncementDto;
import com.carzapi.digital.model.dto.PageableDto;
import com.carzapi.digital.model.dto.PrivilegeDto;
import com.carzapi.digital.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-zapi/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping
    public AnnouncementRequest createAnnouncement(@RequestBody AnnouncementRequest announcementRequest) {
        return announcementService.createAnnouncement(announcementRequest);
    }

    @GetMapping
    public PageableDto<LightAnnouncementDto> getLightAnnouncements(@RequestParam(defaultValue = "0") Integer page,
                                                                   @RequestParam(defaultValue = "10") Integer size,
                                                                   AnnouncementCriteria announcementCriteria) {
        return announcementService.getLightAnnouncements(page, size, announcementCriteria);
    }

    @PutMapping("/{id}/privileges")
    public void addPrivilegeToAnnouncement(@PathVariable Long id,
                                     @RequestBody PrivilegeDto privilegeRequestDto) {
        announcementService.addPrivilegeToAnnouncement(id, privilegeRequestDto);
    }

    @GetMapping("/{id}")
    public AnnouncementDto getDetailedAnnouncement (@PathVariable Long id) {
        return announcementService.getAnnouncement(id);
    }

}
