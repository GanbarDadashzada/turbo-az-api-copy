package com.turboaz.digital.dao.entity;


import com.turboaz.digital.model.enums.Fuel;
import com.turboaz.digital.model.enums.GearBox;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "announcements")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String distanceType;
    private Integer distanceUnit;
    private String ccy;
    private Double price;
    private String ownership;
    private Integer seatCount;
    private String conductorType;
    private Integer motorSize;
    @Enumerated(EnumType.STRING)
    private GearBox gearBox;
    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    private Integer year;
    private Integer motorPower;
    private String vinCode;
    private String description;
    private String fullName;
    private String email;

    @OneToMany(mappedBy = "announcementEntity")
    private List<AnnouncementPrivilegeEntity> announcementPrivilegeEntities;


    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
    private ModelEntity modelEntity;

    @ManyToOne
    @JoinColumn(name = "ban_type_id", referencedColumnName = "id", nullable = false)
    private BanTypeEntity banTypeEntity;

    @ManyToOne
    @JoinColumn(name = "colour_id", referencedColumnName = "id", nullable = false)
    private ColourEntity colourEntity;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private CityEntity cityEntity;

    @ManyToMany
    @JoinTable(name = "announcement_equipment",
            joinColumns = {
                    @JoinColumn(name = "announcement_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
            }
    )
    private List<EquipmentEntity> equipmentEntities;

    @ManyToMany
    @JoinTable(name = "announcement_condition", joinColumns = {
            @JoinColumn(name = "announcement_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "condition_id", referencedColumnName = "id")
    }
    )
    private List<ConditionEntity> conditionEntities;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
