package commons;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Fight  implements Serializable {
    @Id
    public long id;

    public Fight() {
    }
}
