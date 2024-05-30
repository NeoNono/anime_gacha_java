package commons;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Player implements Serializable {

    @Id
    public long id;
    public int balance;


    public Player() {
        this.balance = 100;
    }

    public int getBalance() {
        return balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && balance == player.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
