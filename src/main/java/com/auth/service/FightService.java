package com.auth.service;

import com.auth.model.ExecutionResponse;
import com.worldnavigator.gameplay.fight.FightBody;
import com.worldnavigator.managers.FightManager;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FightService {

  public ExecutionResponse fight(FightBody fightBody) {
    setFightBody(fightBody);
    ExecutionResponse executionResponse = new ExecutionResponse();
    executionResponse.setMessage(FightManager.startFight(fightBody.getUserName()));
    return executionResponse;
  }

  private void setFightBody(FightBody fightBody) {
    Map<String, FightBody> usersInFight = FightManager.getUsersFightBody();
    String userName = fightBody.getUserName();
    usersInFight.get(userName).setChosen(fightBody.getChosen());
  }
}
