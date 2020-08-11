package com.auth.service;

import com.auth.model.ExecutionBody;
import com.auth.model.ExecutionResponse;
import com.auth.model.PlayerEntity;
import com.worldnavigator.configurations.JsonConverter;
import com.worldnavigator.gameplay.CommandLineParser;
import com.worldnavigator.gameplay.ConsolePrinter;
import com.worldnavigator.gameplay.EntitiesGetter;
import com.worldnavigator.gameplay.Player;
import com.worldnavigator.gameplay.commands.Command;
import com.worldnavigator.gameplay.exceptions.IllegalCommandException;
import com.worldnavigator.gameplay.exceptions.WrongCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;

@Service
public class CommandsExecutionService {
  @Autowired private PlayerService playerEntityRepository;
  private final JsonConverter jsonConverter = new JsonConverter();

  public ExecutionResponse executionResult(ExecutionBody executionBody) {
    ExecutionResponse executionResponse = new ExecutionResponse();

    if (playerEntityRepository.findByUserName(executionBody.getUserName()) != null) {
      if (playerNotInMapOrQuit(executionBody)) {
        try {
          executeCommand(executionBody);
        } catch (WrongCommandException
            | IllegalCommandException
            | OperationNotSupportedException e) {
          System.out.println(e.getMessage());
        }
        executionResponse.setMessage(ConsolePrinter.result);
      } else {
        executionResponse.setMessage("waiting for more players please wait");
      }
    } else {
      executionResponse.setMessage("a player entered the room and you lost against him");
    }
    return executionResponse;
  }

  private void executeCommand(ExecutionBody executionBody)
      throws WrongCommandException, IllegalCommandException, OperationNotSupportedException {
    CommandLineParser commandLineParser = new CommandLineParser();
    Command command = commandLineParser.parse(executionBody.getCommand());
    command.execute(EntitiesGetter.getPlayer(executionBody.getUserName()));
  }

  private boolean playerNotInMapOrQuit(ExecutionBody executionBody) {
    Player player = null;
    PlayerEntity playerEntity = null;
    if (playerEntityRepository.findByUserName(executionBody.getUserName()) != null) {
      playerEntity = playerEntityRepository.findByUserName(executionBody.getUserName());
      player = jsonConverter.convertJsonToPlayer(playerEntity.getPlayer());
      return player.getMapId() != -1 || executionBody.getCommand().equalsIgnoreCase("quit");
    }
    return false;
  }
}
