CREATE TABLE base_stations (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255),
    x FLOAT,
    y FLOAT,
    detection_radius_in_meters FLOAT
);

CREATE INDEX idx_base_stations_x ON base_stations (x);
CREATE INDEX idx_base_stations_y ON base_stations (y);

INSERT INTO base_stations (id, name, x, y, detection_radius_in_meters)
VALUES
    ('b1', 'Base Station 1', 10.0, 20.0, 100.0),
    ('b2', 'Base Station 2', 30.0, 40.0, 150.0),
    ('b3', 'Base Station 3', 50.0, 60.0, 120.0),
    ('b4', 'Base Station 4', 70.0, 80.0, 80.0),
    ('b5', 'Base Station 5', 90.0, 100.0, 200.0),
    ('b6', 'Base Station 6', 110.0, 120.0, 90.0),
    ('b7', 'Base Station 7', 130.0, 140.0, 180.0),
    ('b8', 'Base Station 8', 150.0, 160.0, 70.0),
    ('b9', 'Base Station 9', 170.0, 180.0, 60.0),
    ('b10', 'Base Station 10', 190.0, 200.0, 110.0);

CREATE TABLE mobile_stations (
    id VARCHAR(36) PRIMARY KEY,
    last_known_x FLOAT,
    last_known_y FLOAT
);

CREATE INDEX idx_mobile_stations_x ON mobile_stations (last_known_x);
CREATE INDEX idx_mobile_stations_y ON mobile_stations (last_known_y);

INSERT INTO mobile_stations (id, last_known_x, last_known_y)
VALUES
    ('m1', 15.0, 25.0),
    ('m2', 35.0, 45.0),
    ('m3', 55.0, 65.0),
    ('m4', 75.0, 85.0),
    ('m5', 95.0, 105.0),
    ('m6', 115.0, 125.0),
    ('m7', 135.0, 145.0),
    ('m8', 155.0, 165.0),
    ('m9', 175.0, 185.0),
    ('m10', 195.0, 205.0);

CREATE TABLE report (
    id UUID PRIMARY KEY,
    base_station_id UUID,
    mobile_station_id UUID,
    distance FLOAT,
    timestamp TIMESTAMP,
    FOREIGN KEY (base_station_id) REFERENCES base_stations (id),
    FOREIGN KEY (mobile_station_id) REFERENCES mobile_stations (id)
);