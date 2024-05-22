package commons;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;


//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
 //       property = "id")
@Entity
public class Character {
    @Id
    public long code;
    public String rarity;
    public int health;
    public int stamina;
    public int damage;
    public int price;

    public Character() {
        int sellPrice = (int) (this.price*0.65);
    }
}


