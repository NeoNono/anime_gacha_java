package commons;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class OwnedCharacter implements Serializable {
//    @Id
//    public long id;
//    @Id
//    public long code;

    @Id
    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Character character;

    @Id
    @ManyToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    private Player player;

    public OwnedCharacter() {
        //for object mappers
    }

    public OwnedCharacter(Character character, Player player) {
        this.character = character;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCharacter that = (OwnedCharacter) o;
        return Objects.equals(character, that.character) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, player);
    }

    @Override
    public String toString() {
        return "OwnedCharacter{" +
                "character=" + character +
                ", player=" + player +
                '}';
    }
}
