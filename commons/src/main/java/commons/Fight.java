package commons;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Fight  implements Serializable {
    @Id
    public long id;

    public Character enemyCharacter;

    public Fight() {
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
