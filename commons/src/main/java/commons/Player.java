package commons;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Entity
public class Player implements Serializable {

//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    public long id;
    public int balance;
//    public HashSet<Character> characterCollection;


    public Player() {
        this.balance = 100;
//        this.characterCollection = new
    }
}
