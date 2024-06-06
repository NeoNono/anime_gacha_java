# Course Work: Design and Development of a Multi-Layered Web Application for a Game GachaGirls

## Introduction

The purpose of the course work is the development of a multi-layered web application for the text gacha simulator with a turn-based strategy element.
The game functionality allows us to obtain different characters when pulling a roulette, upgrading them and fighting various enemies for a reward.


The application is built according to a multi-layered architecture, which includes the following steps:

- Design and development of the web layer,
- Design and development of the service layer 
- Design and development of the storage layer
- Design and development of logging

In this work, the design and development process of each of these layers, as well as the application deployment process, will be discussed in detail.

## Application Functionality

### Player management

- Creating a new player with default balance and starter character
- Deleting a player by Id
- Checking the player's details
- Managing characters in player's collection like selling or upgrading 

### Character management

- Get details of the specific character
- Delete character from your collection
- Get the Ascii art of the specific character
- Get list of all possible characters

### Roulette management

- Make a pull for characters with different probabilities

### Fight management

- Get the list of available fights with numerous difficulties
- Fight a chosen enemy

## Part 1 - Web Layer Design and Development (REST requests)

### Introduction 

The web layer is a crucial component of any web application since it is responsible for processing requests from clients and returning responses.
In our application we use Spring Boot for the implementation of RESTful web services. 
This layer includes controllers that handle HTTP requests and call appropriate services to execute business logic.

### Theoretical  

REST (Representational State Transfer) is an architectural style for distributed systems such as web applications. 
The basic idea behind REST is to use standard HTTP methods (GET, POST, PUT, DELETE) to perform operations on resources identified by URLs. 
RESTful web services are easy to use, high performance and scalable.

The key principles of REST are:

- **Stateless state:** Every request from the client to the server must contain all the necessary information to process the request. The server does not store any information about the client between requests.
- **Unified interface:** standard HTTP methods are used to interact with resources. This makes the system easy to understand and use.
- **Client-server architecture:** The client and server are independent components, allowing them to be developed separately from each other.
- **Caching:** The server response can be cached by the client to reduce server load and improve performance.
- Layered system: additional layers (proxies, gateways) can be placed between the client and the server, which helps to improve the scalability and security of the system.

Spring Boot is a popular Java-based web application framework that provides ready-to-use components and tools for implementing RESTful web services.
Using Spring Boot allows you to quickly configure and start a web server, implement request routing and data processing.

### Implementation

#### Player controller

```java


@RestController
public class PlayerController {

private final PlayerService playerService;

private final RouletteService rouletteService;
private final CharacterService characterService;

    public PlayerController(PlayerService playerService,  RouletteService rouletteService, CharacterService characterService) {
        this.playerService = playerService;
        this.rouletteService = rouletteService;
        this.characterService = characterService;
    }

    @GetMapping(path = "/players/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") long id) {
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @DeleteMapping(path = "/players/{id}")
    public ResponseEntity<Player> deleteById(@PathVariable long id) {
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        Player dp = playerService.getPlayerById(id);
        playerService.deletePlayer(id);
        return ResponseEntity.ok(dp);
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer() {
        Player player = playerService.createPlayer();
        return ResponseEntity.ok(player);
    }

    @GetMapping("/players/{id}/balance")
    public ResponseEntity<Integer> getPlayerBalance(@PathVariable long id) {
        int balance = playerService.getPlayerBalance(id);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/players/{id}/characters")
    public ResponseEntity<List<OwnedCharacter>> ownedCharacters(@PathVariable long id){
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getPlayerCharacters(id));
    }

    @PatchMapping("/players/{id}/characters/{code}/sell")
    public ResponseEntity<List<OwnedCharacter>> sellOwnedCharacter(@PathVariable long id, @PathVariable long code){
        if (id < 0 || code < 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(playerService.sellGivenCharacter(id, code));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/players/{id}/characters/{code}/upgrade")
    public ResponseEntity<OwnedCharacter> upgradeCharacter(@PathVariable long id, @PathVariable long code){
        if (id < 0 || code < 0) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok(playerService.upgradeOwnedCharacter(id, code));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

}

```

#### Character controller

```java

@RestController
public class CharacterController {

    private final CharacterService characterService;

    private final RouletteService rouletteService;

    private final PlayerService playerService;


    @Autowired
    public CharacterController(CharacterService characterService,
     RouletteService rouletteService, PlayerService playerService) {
        this.characterService = characterService;
        this.rouletteService = rouletteService;
        this.playerService = playerService;
    }


    @PostMapping("/characters/seed")
    public void seedDatabase(){
        this.characterService.seedDatabase();
    }

    @GetMapping(path = "/characters/{code}")
    public ResponseEntity<Character> getById(@PathVariable long code) {
        if (code < 0 || !characterService.exists(code)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(characterService.getCharacterById(code));
    }

    @DeleteMapping(path = "/characters/{code}")
    public ResponseEntity<Character> deleteById(@PathVariable long code) {
        if (code < 0 || !characterService.exists(code)) {
            return ResponseEntity.badRequest().build();
        }
        Character ch = characterService.getCharacterById(code);
        characterService.deleteCharacter(code);
        return ResponseEntity.ok(ch);
    }

    @GetMapping("/characters/{code}/appearance")
    public ResponseEntity<String> getCharacterAppearance(@PathVariable long code) {
        String appearance = characterService.getCharacterAppearance(code);
        return ResponseEntity.ok(appearance);
    }

    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getPossibleCharacters() {
        List<Character> possibleCharacters = characterService.getPossibleCharacters();
        return ResponseEntity.ok(possibleCharacters);
    }
}
```

#### Roulette controller

```java

@RestController
public class RouletteController {

    private final RouletteService rouletteService;

    private final PlayerService playerService;

    private final CharacterService characterService;

    private final FightService fightService;

    public RouletteController(RouletteService rouletteService, PlayerService playerService,
                              CharacterService characterService, FightService fightService) {
        this.rouletteService = rouletteService;
        this.playerService = playerService;
        this.characterService = characterService;
        this.fightService = fightService;
    }

    @PostMapping("/roulette/pulls")
    public ResponseEntity<List<OwnedCharacter>> pullRoulette(@RequestParam long playerId){
        if (playerId < 0 || !playerService.exists(playerId)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<List<OwnedCharacter>> ownedCharacters = this.rouletteService.pullRoulette(playerId);
        if (ownedCharacters.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ownedCharacters.get());
    }


}

```

#### Fight controller

```java


@RestController
public class FightController {

    private final RouletteService rouletteService;

    private final PlayerService playerService;

    private final CharacterService characterService;

    private final FightService fightService;

    public FightController(RouletteService rouletteService, PlayerService playerService,
                           CharacterService characterService, FightService fightService) {
        this.rouletteService = rouletteService;
        this.playerService = playerService;
        this.characterService = characterService;
        this.fightService = fightService;
    }

    @PostMapping("/fights/seed")
    public void seedDatabase(){
        this.fightService.seedDatabase();
    }

    @GetMapping("/fights")
    public ResponseEntity<List<Fight>> possibleFights(){
        List<Fight> fights = fightService.getPossibleFights();
        return ResponseEntity.ok(fights);
    }

    @PostMapping("/players/{id}/characters/{code}/fights/{fightId}")
    public ResponseEntity<Player> fightAnEnemy(@PathVariable long id, @PathVariable long code, @PathVariable long fightId){
        if (id < 0 || code < 0) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok(fightService.fightEnemy(id, code, fightId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
```

## Part 2 - Design and Development of the Service Layer

### Introduction

The service layer is the key component of the layered architecture responsible for the business logic of the application.
It includes all the rules and algorithms needed to perform operations such as user and character management, fight and roulette performing.
The use of the service layer allows you to separate the logic of data processing from the web layer, which simplifies the maintenance and expansion of the application's functionality.

### Theoretical p

The main principles of service layer design are:

- **Encapsulation:** business logic should be hidden from external components, which allows to reduce dependencies between components and increase security.
- **Reusability:** services must be flexible enough to be reused in different application contexts.
- **Testing:** services should be easily testable, allowing for quick detection and correction of errors.
- **Scalability:** services should be designed so that they can be easily scaled as the load increases.

In our application, the service layer is implemented using Spring Service, which provides inversion of control and dependencies. 
This makes it easy to manage the creation and lifecycle of services, as well as their dependencies.

### Implementation

#### Player service 

```java

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    private final OwnedCharacterRepository ownedCharacterRepository;

    private final CharacterRepository characterRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, OwnedCharacterRepository ownedCharacterRepository, CharacterRepository characterRepository) {
        this.playerRepository = playerRepository;
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.characterRepository = characterRepository;
    }

    public Character getDefaultCharacter() {
        return this.characterRepository.findByName("Vendy").orElseThrow(() -> new RuntimeException("Default character not found"));
    }

    public boolean exists(long id) {
        return playerRepository.existsById(id);
    }

    public Player getPlayerById(long id) {
        return playerRepository.findById(id).get();
    }
    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }


    public Player createPlayer() {
        Player player = playerRepository.save(new Player());

        ownedCharacterRepository.save(new OwnedCharacter(new OwnedCharacterId(getDefaultCharacter(), player)));
        return player;
    }

    public int getPlayerBalance(long id){
        Player player = playerRepository.findById(id).orElse(null);
        return player.getBalance();
    }


      public List<OwnedCharacter> getPlayerCharacters(long id){
        return ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
    }


    public List<OwnedCharacter> sellGivenCharacter(long id, long code){
        if(code == getDefaultCharacter().code) {throw new IllegalStateException("This is your default character, it can't be sold!");}
        Player player = playerRepository.findById(id).orElseThrow();
        Character character = characterRepository.findById(code).orElseThrow();
        OwnedCharacter ownedCharacter = ownedCharacterRepository.findById(new OwnedCharacterId(character, player)).orElseThrow();
        player.setBalance((int) (player.getBalance() + character.getSELL_COEFF()));
        ownedCharacterRepository.delete(ownedCharacter);
        playerRepository.save(player);

        return ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
    }

    public OwnedCharacter upgradeOwnedCharacter(long id, long code){
        Player player = playerRepository.findById(id).orElseThrow();
        Character character = characterRepository.findById(code).orElseThrow();
        OwnedCharacter ownedCharacter = ownedCharacterRepository.findById(new OwnedCharacterId(character, player)).orElseThrow();

        if(player.getBalance()<500){
            throw new IllegalStateException("You don't have enough money for an upgrade!");
        }
        player.setBalance(player.getBalance()-500);
        ownedCharacter.setDamage(ownedCharacter.getDamage()+1);
        ownedCharacter.setHealth(ownedCharacter.getHealth()+4);
        ownedCharacter.setStamina(ownedCharacter.getStamina()+2);
        ownedCharacter = ownedCharacterRepository.save(ownedCharacter);
        playerRepository.save(player);

        return ownedCharacter;
    }

}
```

#### Character service

```java

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final OwnedCharacterRepository ownedCharacterRepository;

    private final PlayerRepository playerRepository;


    public List<Character> possibleCharacters =
            List.of(new Character("Raiden Shogun", RARE, 38, 20, 8, 4000,raidenAscii()),
                    new Character("Shrek", LEGENDARY, 50, 25, 9, 5000, shrekAscii()),
                    new Character("Ayanami Rei", REGULAR, 30, 15, 5, 2000, ayanamiAscii()),
                    new Character("Mitsuri Kanrodji", REGULAR, 27, 15, 6, 2500, mitsuriAscii()),
                    new Character("Boa Hancock", LEGENDARY, 50, 25, 10, 5500, boaAscii()),
                    new Character("Asuka", RARE, 38, 20, 7, 3500, asukaAscii()),
                    new Character("Nezuko", RARE, 40, 20,6, 3500, nezukoAscii()),
                    new Character("Sakura Haruno", REGULAR, 30, 15,5, 2000, sakuraAscii()),
                    new Character("Yumeko Yabami", REGULAR, 30, 15,5, 2000, yumekoAscii() ),
                    new Character("Vendy", REGULAR, 25, 15,4, 1500, vendyAscii())
            );



    @Autowired
    public CharacterService(CharacterRepository characterRepository, OwnedCharacterRepository ownedCharacterRepository,
                            PlayerRepository playerRepository){
        this.characterRepository = characterRepository;
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.playerRepository = playerRepository;
    }

    public List<Character> getPossibleCharacters() {
        return this.characterRepository.findAll();
    }

    public Character getCharacterById(long code) {
        return characterRepository.findById(code).get();
    }
    public void deleteCharacter(long code) {
        characterRepository.deleteById(code);
    }

    public void seedDatabase(){
        for(Character character : possibleCharacters){
            this.characterRepository.save(character);
        }
    }

    public String getCharacterAppearance(long code){
        Character character = characterRepository.findById(code).orElse(null);
        return character.getAppearance();
    }

    public List<OwnedCharacter> addCharacterToPlayer(long id, long code){
        Player player = playerRepository.findById(id).orElseThrow();
        Character character = characterRepository.findById(code).orElseThrow();
        OwnedCharacterId compositeId = new OwnedCharacterId(character, player);

        if (ownedCharacterRepository.existsById(compositeId))
            throw new IllegalStateException("Cannot save duplicate characters.");

        OwnedCharacter ownedCharacter = new OwnedCharacter(new OwnedCharacterId(character, player));
        ownedCharacterRepository.save(ownedCharacter);

        return ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
    }


    public boolean exists(long code) {
        return characterRepository.existsById(code);
    }
}
```

#### Roulette service

```java

@Service
public class RouletteService {

    private final OwnedCharacterRepository ownedCharacterRepository;

    private final PlayerRepository playerRepository;

    private final CharacterRepository characterRepository;

    private final CharacterService characterService;
    @Autowired
    public RouletteService(OwnedCharacterRepository ownedCharacterRepository, PlayerRepository playerRepository,
                           CharacterRepository characterRepository, CharacterService characterService) {
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.playerRepository = playerRepository;
        this.characterRepository = characterRepository;
        this.characterService = characterService;
    }

    public Optional<List<OwnedCharacter>> pullRoulette(long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        List<OwnedCharacter> ownedCharacters = ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
        if (player.getBalance() < 50) {
            throw new IllegalStateException("You don't have enough gold for a pull");
        }
        Random rng = new Random();
        int roll = rng.nextInt(100);

        Optional<Character> character;
        if (roll <= 4) {
            character = this.getRandomCharacter(ownedCharacters, Rarity.LEGENDARY);
        }
        else if (roll <=29){
            character = this.getRandomCharacter(ownedCharacters, Rarity.RARE);
        }
        else
            character = this.getRandomCharacter(ownedCharacters, Rarity.REGULAR);
        //duplicates
        if (character.isEmpty()) {
            player.setBalance(player.getBalance() + 200);
            playerRepository.save(player);
            return Optional.empty();
        } else {
            return Optional.of(characterService.addCharacterToPlayer(id, character.get().code)); //add character to collection
        }
    }

    private Optional<Character> getRandomCharacter(List<OwnedCharacter> ownedCharacters, Rarity rarity) {
        Random rng = new Random();
        List<Character> legendaryCharacters = this.characterRepository.findAllByRarity(rarity);
        int roll = rng.nextInt(legendaryCharacters.size());
        Character rolledChar = legendaryCharacters.get(roll);
        if (ownedCharacters.stream()
                .map(ownedCharacter -> ownedCharacter.ownedCharacterId.character)
                .toList().contains(rolledChar)) {
            return Optional.empty();
        }
        return Optional.of(rolledChar);
    }
}
```

#### Fight service

```java
@Service
public class FightService {

    private final CharacterRepository characterRepository;
    private final OwnedCharacterRepository ownedCharacterRepository;

    private final PlayerRepository playerRepository;

    private final FightRepository fightRepository;
    @Autowired
    public FightService(CharacterRepository characterRepository, OwnedCharacterRepository ownedCharacterRepository,
                        PlayerRepository playerRepository, FightRepository fightRepository) {
        this.characterRepository = characterRepository;
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.playerRepository = playerRepository;
        this.fightRepository = fightRepository;
    }

    public List<Fight> getPossibleFights() {
        return this.fightRepository.findAll();
    }
    private List<Fight> getFights() {
        List<Fight> enemiesList = List.of(
                new Fight(new Character("YaeMiko", RARE, 45, 20, 6, 0, yaeAscii())),
                new Fight(new Character("Mikasa", LEGENDARY, 55, 25, 10, 0, mikasaAscii())),
                new Fight(new Character("Lucy", REGULAR, 25, 10, 5, 0, lucyAscii())));
        return enemiesList;
    }
    public void seedDatabase(){
        List<Fight> fights = new LinkedList<>();
        for (Fight fight : getFights()) {
            fight.enemyCharacter = this.characterRepository.save(fight.enemyCharacter);
            fights.add(fight);
        }

        for(Fight fight : fights){
            this.fightRepository.save(fight);
        }
    }
    public Player fightEnemy(long id, long code, long fightId){
        Player player = playerRepository.findById(id).orElseThrow();
        Character character = characterRepository.findById(code).orElseThrow();
        OwnedCharacter ownedCharacter = ownedCharacterRepository.findById(new OwnedCharacterId(character, player)).orElseThrow();
        Fight fight = fightRepository.findById(fightId).orElseThrow();

        Random rng = new Random();
        int charHealth = ownedCharacter.getHealth();
        int enemyHealth = fight.enemyCharacter.health;
        int charStamina = ownedCharacter.getStamina();
        int enemyStamina = fight.enemyCharacter.getStamina();
        int charDamage;
        int enemyDamage;
        boolean isVictory = false;

        while (charHealth > 0 && enemyHealth > 0) {
            // step 1: player turn
            charDamage = rng.nextInt(ownedCharacter.getDamage() + Math.max(ownedCharacter.getStamina(), 0));
            charDamage = charDamage > ownedCharacter.getDamage() ? ownedCharacter.getDamage() : charDamage;

            enemyHealth -= charDamage;
            charStamina -= 3;

            if (enemyHealth < 0) {
                isVictory = true;
                break;
            }

            // step 1: enemy turn
            enemyDamage = rng.nextInt(fight.enemyCharacter.getDamage() + Math.max(fight.enemyCharacter.getStamina(), 0));
            enemyDamage = charDamage > fight.enemyCharacter.getDamage() ? fight.enemyCharacter.getDamage() : enemyDamage;

            charHealth -= enemyDamage;
            enemyStamina -= 3;
        }

        if (isVictory){
            player.setBalance(player.getBalance()+100);

        }  else  player.setBalance(player.getBalance()-50);



        return playerRepository.save(player);

    }
}
```

## Part 3 - Design and Development of the Storage Layer

### Introduction

The storage layer is responsible for storing and managing data in the application.
It provides interaction with the database, execution of CRUD operations (Create, Read, Update, Delete) and maintains data integrity.
Our application uses a relational database and interacts with it using JPA (Java Persistence API) and Spring Data JPA.

### Theoretical

The main principles of designing the storage layer are:

- **Data Normalization:** The process of organizing data in a database to minimize redundant data and ensure its integrity.
- **Relationships between tables:** establishing associations between different database tables to ensure data integrity.
- **Transactionality:** ensuring that all data operations are performed atomically, i.e. either all changes are made or none.
- **Caching:** Using cache to reduce database load and improve system performance.

  Our application uses the following main components:

- **Entity classes:** Java classes corresponding to tables in the database.
- **Repository interfaces:** interfaces for performing CRUD operations with data.
- **JPQL (Java Persistence Query Language):** query language for interacting with data in a database.

### Implementation

#### Entities

#### Player

```java

@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    public long id;
    public int balance;


    public Player() {
        this.balance = 100;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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
```

#### Character
```java

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


```

#### Fight

```java

@Entity
public class Fight  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
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
```

#### OwnedCharacter

```java

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
```

#### OwnedCharacterId

```java

@Embeddable
public class OwnedCharacterId implements Serializable {

    @ManyToOne
    @MapsId("characterCode")
    @JoinColumn(name = "code", referencedColumnName = "code")
    public Character character;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "id",referencedColumnName = "id")
    public Player player;

    public OwnedCharacterId() {
    }

    public OwnedCharacterId(Character character, Player player) {
        this.character = character;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCharacterId that = (OwnedCharacterId) o;
        return Objects.equals(character, that.character) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, player);
    }
}
```
Also the class Character is using enum Rarity for splitting characters on 3 categories: Regular, Rare and Legendary.

#### Enum Rarity

```java
public enum Rarity {

    REGULAR,
    RARE,
    LEGENDARY

}
```

#### Repository interfaces

#### Character repository

```java
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    public Optional<Character> findByName(String name);
    public List<Character> findAllByRarity(Rarity rarity);
}
```

#### Player repository

```java
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
```

#### Fight repository

```java
@Repository
public interface FightRepository extends JpaRepository<Fight, Long> {

}
```

#### OwnedCharacter repository

```java
@Repository
public interface OwnedCharacterRepository extends JpaRepository<OwnedCharacter, OwnedCharacterId> {

    public List<OwnedCharacter> findAllByOwnedCharacterIdPlayerId(long id);
}

```

## Part 4 - Design and development of logging

### Introduction 

Logging is an important part of any software application because it allows you to track events and actions in the system, which helps to detect and eliminate errors,
monitor system performance, and ensure security.In our application, logging is implemented using aspect-oriented programming (AOP) in combination with the SLF4J library and Logback.

### Theoretical

Aspect Oriented Programming (AOP) allows you to separate the business logic of the application from the technical logic such as logging, security, transactional, etc. The main concepts of AOP are:

- **Aspect:** A module containing technical logic that can be applied to different parts of an application.
- **Join Point:** A place in the code where an aspect can be applied.
- **Pointcut:** An expression that defines the connection points to which the aspect will be applied.
- **Advice:** code executed at attachment points.

In case of GacgaGirls the Spring Boot application can work perfectly fine without files like logback-spring.xml because Spring Boot provides a default logging configuration that is sufficient for many applications.
We can configure basic logging settings in application.properties file:

#### Implementation

#### application.properties

```java
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# use one of these alternatives...
# ... purely in-memory, wiped on restart, but great for testing
#spring.datasource.url=jdbc:h2:mem:testdb
# ... persisted on disk (in project directory)
spring.datasource.url=jdbc:h2:file:./database

# enable DB view on http://localhost:8080/h2-console
spring.h2.console.enabled=true

# strategy for table (re-)generation
spring.jpa.hibernate.ddl-auto=update
# show auto-generated SQL commands
#spring.jpa.hibernate.show_sql=true

```

## Application deployment


