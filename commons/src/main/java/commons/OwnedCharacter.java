package commons;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class OwnedCharacter implements Serializable {

    @EmbeddedId
    public OwnedCharacterId ownedCharacterId;

    public OwnedCharacter() {

    }

    public OwnedCharacter(OwnedCharacterId ownedCharacterId) {
        this.ownedCharacterId = ownedCharacterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCharacter that = (OwnedCharacter) o;
        return Objects.equals(ownedCharacterId, that.ownedCharacterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownedCharacterId);
    }

}
