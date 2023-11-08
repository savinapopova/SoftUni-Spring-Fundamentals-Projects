package com.example.hotel.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "double_deluxe_rooms")
public class DoubleDeluxeRoom extends Room {

        private boolean hasSeaView;

        public DoubleDeluxeRoom() {
        }

        public boolean isHasSeaView() {
            return hasSeaView;
        }

        public DoubleDeluxeRoom setHasSeaView(boolean hasSeaView) {
            this.hasSeaView = hasSeaView;
            return this;
        }
}
