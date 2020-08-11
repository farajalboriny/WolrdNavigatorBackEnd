package com.auth.web;

import com.auth.model.ExecutionBody;
import com.auth.model.ExecutionResponse;
import com.auth.model.Fetch;
import com.auth.service.CommandsExecutionService;
import com.auth.service.FetchGameStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class GameplayController {

  @Autowired CommandsExecutionService commandsExecutionService;
  @Autowired FetchGameStatusService fetchGameStatusService;

  @PostMapping(
      value = "/execute",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ExecutionResponse> executeCommand(
      @RequestBody ExecutionBody executionBody) {

    return Optional.of(Optional.empty())
        .map(u -> ResponseEntity.ok().body(commandsExecutionService.executionResult(executionBody)))
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  @RequestMapping(value = "/fetch")
  public ResponseEntity<Fetch> fetch(@RequestBody ExecutionBody executionBody) {

    return Optional.of(Optional.empty())
        .map(u -> ResponseEntity.ok().body(fetchGameStatusService.fetchGameStatus(executionBody)))
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
