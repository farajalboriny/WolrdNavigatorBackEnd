package com.auth.web;

import com.auth.model.ExecutionResponse;
import com.auth.service.FightService;
import com.worldnavigator.gameplay.fight.FightBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class FightController {
  @Autowired FightService fightService;

  @PostMapping(
      value = "/fight",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ExecutionResponse> fight(@RequestBody FightBody fightBody) {

    return Optional.of(Optional.empty())
        .map(u -> ResponseEntity.ok().body(fightService.fight(fightBody)))
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }
}
