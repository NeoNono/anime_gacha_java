package commons;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;


@Entity
public class OwnedCharacter {
    @Id
    public long id;
    @Id
    public long code;


    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private Character character;

    @ManyToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    private Player player;

    public OwnedCharacter() {
        //for object mappers
    }

    public OwnedCharacter(long id, long code, Character character, Player player) {
        this.id = id;
        this.code = code;
        this.character = character;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCharacter that = (OwnedCharacter) o;
        return id == that.id && code == that.code && Objects.equals(character, that.character)
                && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, character, player);
    }

    @Override
    public String toString() {
        return "OwnedCharacter{" +
                "id=" + id +
                ", code=" + code +
                ", character=" + character +
                ", player=" + player +
                '}';
    }
}
