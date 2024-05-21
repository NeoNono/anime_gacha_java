package commons;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fight {
    @Id
    public long id;

    public Fight() {
    }
}
