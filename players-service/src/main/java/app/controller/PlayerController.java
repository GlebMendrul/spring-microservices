package app.controller;

import app.model.Player;
import app.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

/**
 * Created by gleb on 3/12/2017.
 */
@RestController
@RequestMapping("/players")
@Slf4j
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll() {
        log.info("retrieving all players");
        List<Player> players = playerService.getAll();
        if (isEmpty(players)) {
            log.info("players not found");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(players);
    }

    @RequestMapping(value = "player", method = RequestMethod.GET)
    public ResponseEntity<Player> getByName(
            @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        log.info("retrieving player by firstName={}, lastName={}", firstName, lastName);
        requireNonNull(firstName);
        requireNonNull(lastName);

        Optional<Player> player = playerService.getByName(firstName, lastName);
        return player.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Player player) {
        log.info("adding player {}", player);
        requireNonNull(player);
        Player newPlayer = playerService.add(player);

        log.info("created new {}", newPlayer);
        URI uri = fromCurrentRequest().
                path("/players").
                buildAndExpand(newPlayer).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Player> update(@RequestBody Player player) {
        log.info("updating player {}", player);
        validatePlayer(player);

        Optional<Player> updated = playerService.update(player);
        return updated.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody Player player) {
        log.info("deleting player {}", player);
        requireNonNull(player);
        requireNonNull(player.getId());

        playerService.delete(player);
        return ResponseEntity.noContent().build();
    }

    private void validatePlayer(Player player) {
        requireNonNull(player);
        requireNonNull(player.getId());
        requireNonNull(player.getRating());
        requireNonNull(player.getScores());
        requireNonNull(player.getTeamName());
    }

}
