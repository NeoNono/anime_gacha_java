package commons;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OwnedCharacterId implements Serializable {

    @ManyToOne
    @MapsId("characterCode")
    @JoinColumn(name = "code", referencedColumnName = "code")
    public Character character;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "id",referencedColumnName = "id")
    public Player player;

    public OwnedCharacterId() {
    }

    public OwnedCharacterId(Character character, Player player) {
        this.character = character;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCharacterId that = (OwnedCharacterId) o;
        return Objects.equals(character, that.character) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, player);
    }
}
