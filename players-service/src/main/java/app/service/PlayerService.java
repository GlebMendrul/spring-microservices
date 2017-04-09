package app.service;

import app.model.Player;
import app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by gleb on 3/12/2017.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    public Player add(Player player) {
        return playerRepository.save(player);
    }

    public void delete(Player player) {
        playerRepository.delete(player);
    }

    public Optional<Player> getByName(String firstName, String lastName) {
        return playerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Transactional
    public Optional<Player> update(Player player) {
        Player oldPlayer = playerRepository.findOne(player.getId());
        if (oldPlayer == null) {
            return Optional.empty();
        }
        oldPlayer.setRating(player.getRating());
        oldPlayer.setScores(player.getScores());
        oldPlayer.setTeamName(player.getTeamName());
        playerRepository.save(oldPlayer);
        return Optional.of(oldPlayer);
    }
}
