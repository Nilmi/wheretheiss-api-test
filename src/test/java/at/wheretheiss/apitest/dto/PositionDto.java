package at.wheretheiss.apitest.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PositionDto implements Serializable {

    private static final long serialVersionUID = 7401375127786947825L;

    private String name;
    private int id;
    private double latitude;
    private double longitude;
    private double altitude;
    private double velocity;
    private String visibility;
    private double footprint;
    private int timestamp;
    private double daynum;
    private double solar_lat;
    private double solar_lon;
    private String units;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionDto that = (PositionDto) o;
        return id == that.id && Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Double.compare(that.altitude, altitude) == 0 && Double.compare(that.velocity, velocity) == 0 && timestamp == that.timestamp && Double.compare(that.daynum, daynum) == 0 && Double.compare(that.solar_lat, solar_lat) == 0 && Double.compare(that.solar_lon, solar_lon) == 0 && name.equals(that.name) && visibility.equals(that.visibility) && units.equals(that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, timestamp, units);
    }

    public PositionDto(List<String> fields) {
        this.name = fields.get(0);
        this.id = Integer.parseInt(fields.get(1));
        this.latitude = Double.parseDouble(fields.get(2));
        this.visibility = fields.get(3);
        this.timestamp = Integer.parseInt(fields.get(4));
        this.daynum = Double.parseDouble(fields.get(5));
        this.solar_lat = Double.parseDouble(fields.get(6));
        this.solar_lon = Double.parseDouble(fields.get(7));
        this.longitude = Double.parseDouble(fields.get(8));
        this.altitude = Double.parseDouble(fields.get(9));
        this.velocity = Double.parseDouble(fields.get(10));
        this.units = fields.get(11);
    }

    public PositionDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public double getFootprint() {
        return footprint;
    }

    public void setFootprint(double footprint) {
        this.footprint = footprint;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public double getDaynum() {
        return daynum;
    }

    public void setDaynum(double daynum) {
        this.daynum = daynum;
    }

    public double getSolar_lat() {
        return solar_lat;
    }

    public void setSolar_lat(double solar_lat) {
        this.solar_lat = solar_lat;
    }

    public double getSolar_lon() {
        return solar_lon;
    }

    public void setSolar_lon(double solar_lon) {
        this.solar_lon = solar_lon;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

}
