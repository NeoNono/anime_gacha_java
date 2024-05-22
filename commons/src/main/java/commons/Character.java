package commons;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;


//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
 //       property = "id")
@Entity
public class Character implements Serializable {
    @Id
    public long code;
    public String rarity;
    public int health;
    public int stamina;
    public int damage;
    public int price;


    public Character() {
        //for object mappers
    }

    public Character(long code, String rarity, int health, int stamina, int damage, int price) {
        this.code = code;
        this.rarity = rarity;
        this.health = health;
        this.stamina = stamina;
        this.damage = damage;
        this.price = price;
        int sellPrice = (int) (this.price*0.65);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return code == character.code && health == character.health && stamina == character.stamina
                && damage == character.damage && price == character.price
                && Objects.equals(rarity, character.rarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, rarity, health, stamina, damage, price);
    }

    @Override
    public String toString() {
        return "Character{" +
                "code=" + code +
                ", rarity='" + rarity + '\'' +
                ", health=" + health +
                ", stamina=" + stamina +
                ", damage=" + damage +
                ", price=" + price +
                '}';
    }
}


