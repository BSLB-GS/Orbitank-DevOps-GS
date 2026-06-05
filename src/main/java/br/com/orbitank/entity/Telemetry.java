package br.com.orbitank.entity;

public class Telemetry {
    private String stationCode;
    private Double volumeLevel;
    private Double temperature;
    private String timestamp;

    // Getters e Setters
    public String getStationCode() { return stationCode; }
    public Double getVolumeLevel() { return volumeLevel; }
    public Double getTemperature() { return temperature; }
    public String getTimestamp() { return timestamp; }
}