package com.claytonpereira.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "report", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"base_station_id", "mobile_station_id"})
})
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "base_station_id", referencedColumnName = "id")
    private BaseStation baseStation;

    @ManyToOne
    @JoinColumn(name = "mobile_station_id", referencedColumnName = "mobile_id")
    private MobileStation mobileStation;

    private float distance;

    private Timestamp timestamp;

}
