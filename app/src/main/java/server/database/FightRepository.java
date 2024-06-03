package server.database;

import commons.Fight;
import commons.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightRepository extends JpaRepository<Fight, Long> {
}