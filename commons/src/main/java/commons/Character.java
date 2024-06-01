package commons;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static commons.Rarity.*;


//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
 //       property = "id")
@Entity
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long code;
    public String name;
    public Rarity rarity;

    public int health;
    public int stamina;
    public int damage;
    @Column(length = 10000)
    public String appearance;
    public int price;

    public final float SELL_COEFF = (float) 0.65;



    public Character() {
        //for object mappers
    }

    public Character( String name, Rarity rarity,  int health, int stamina, int damage, int price, String appearance) {
        this.name = name;
        this.rarity = rarity;
        this.health = health;
        this.stamina = stamina;
        this.damage = damage;
        this.price = price;
        this.appearance = appearance;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getHealth() {
        return health;
    }

    public int getStamina() {
        return stamina;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

    public float getSELL_COEFF() {
        return SELL_COEFF;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getAppearance() {
        return appearance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return code == character.code && health == character.health && stamina == character.stamina
                && damage == character.damage && price == character.price
                && Objects.equals(name, character.name) && rarity == character.rarity
                && Objects.equals(appearance, character.appearance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, rarity, health, stamina, damage, appearance, price, SELL_COEFF);
    }

    @Override
    public String toString() {
        return "Character{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", rarity=" + rarity +
                ", health=" + health +
                ", stamina=" + stamina +
                ", damage=" + damage +
                ", appearance='" + appearance + '\'' +
                ", price=" + price +
                ", SELL_COEFF=" + SELL_COEFF +
                '}';
    }
}


