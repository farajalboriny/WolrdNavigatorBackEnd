package com.auth.model;

import javax.persistence.*;

@Entity
@Table(name = "map")
public class MapEntity {
  @Column(length = Integer.MAX_VALUE)
  private String map;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int mapId;

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
  }

  public int getMapId() {
    return mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
