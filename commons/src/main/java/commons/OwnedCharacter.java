package commons;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class OwnedCharacter implements Serializable {

    @EmbeddedId
    public OwnedCharacterId ownedCharacterId;

    public String name;
    public Rarity rarity;

    public int health;
    public int stamina;
    public int damage;
    @Column(length = 10000)
    public String appearance;
    public int price;

    public final float SELL_COEFF = (float) 0.65;

    public OwnedCharacter() {

    }

    public OwnedCharacter(OwnedCharacterId ownedCharacterId) {
        this.ownedCharacterId = ownedCharacterId;
        this.name = this.ownedCharacterId.character.name;
        this.rarity = this.ownedCharacterId.character.rarity;
        this.health = this.ownedCharacterId.character.health;
        this.stamina = this.ownedCharacterId.character.stamina;
        this.damage = this.ownedCharacterId.character.damage;
        this.appearance = this.ownedCharacterId.character.appearance;
        this.price = this.ownedCharacterId.character.price;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCharacter that = (OwnedCharacter) o;
        return Objects.equals(ownedCharacterId, that.ownedCharacterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownedCharacterId);
    }

}
