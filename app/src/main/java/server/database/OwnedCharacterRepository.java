package server.database;

import commons.OwnedCharacter;
import commons.OwnedCharacterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnedCharacterRepository extends JpaRepository<OwnedCharacter, OwnedCharacterId> {

    public List<OwnedCharacter> findAllByOwnedCharacterIdPlayerId(long id);
}

