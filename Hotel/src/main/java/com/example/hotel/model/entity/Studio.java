package com.example.hotel.model.entity;

import com.example.hotel.model.enums.BedType;
import jakarta.persistence.*;

@Entity
@Table(name = "studios")
public class Studio extends Room {

    @Enumerated(EnumType.STRING)
    @Column(name = "bed_type")
    private BedType bedType;
}
