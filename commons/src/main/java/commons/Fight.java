package commons;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Fight  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    public Character enemyCharacter;

    public Fight() {
    }

    public Fight(Character enemyCharacter) {
        this.enemyCharacter = enemyCharacter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fight fight = (Fight) o;
        return id == fight.id && Objects.equals(enemyCharacter, fight.enemyCharacter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enemyCharacter);
    }

    @Override
    public String toString() {
        return "Fight{" +
                "id=" + id +
                ", enemyCharacter=" + enemyCharacter +
                '}';
    }
}
