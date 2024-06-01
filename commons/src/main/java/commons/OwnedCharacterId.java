package commons;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OwnedCharacterId implements Serializable {

    public long characterCode;
    public long playerId;

    public OwnedCharacterId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCharacterId that = (OwnedCharacterId) o;
        return characterCode == that.characterCode && playerId == that.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterCode, playerId);
    }
}
