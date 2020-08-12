package com.auth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playersCount")
public class PlayersCount {
  private int count;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int mapId;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> players = new ArrayList<>();

  public PlayersCount() {}

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMapId() {
    return mapId;
  }

  public void setMapId(int mapId) {
    this.mapId = mapId;
  }

  public List<String> getPlayers() {
    return players;
  }

  public void setPlayers(List<String> players) {
    this.players = players;
  }

  public void addPlayer(String userName) {
    players.add(userName);
    count++;
  }

  public void removePlayer(String userName) {
    players.remove(userName);
    count--;
  }
}
