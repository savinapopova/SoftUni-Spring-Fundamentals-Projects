package com.example.hotel.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "apartments")
public class Apartment extends Room {

        private boolean hasKitchen;

        public Apartment() {
        }

        public boolean isHasKitchen() {
            return hasKitchen;
        }

        public Apartment setHasKitchen(boolean hasKitchen) {
            this.hasKitchen = hasKitchen;
            return this;
        }
}
