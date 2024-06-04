package server.database;

import commons.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    public Optional<Character> findByName(String name);
}