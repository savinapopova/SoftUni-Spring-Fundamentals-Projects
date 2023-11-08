package com.example.hotel.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "single_rooms")
public class SingleRoom extends Room {

        private boolean hasBalcony;


        public SingleRoom() {
        }

        public boolean isHasBalcony() {
            return hasBalcony;
        }

        public SingleRoom setHasBalcony(boolean hasBalcony) {
            this.hasBalcony = hasBalcony;
            return this;
        }
}
