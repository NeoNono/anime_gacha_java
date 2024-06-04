package server.service;

import commons.*;
import commons.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.CharacterRepository;
import server.database.FightRepository;
import server.database.OwnedCharacterRepository;
import server.database.PlayerRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static commons.Rarity.*;

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

    public static String yaeAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⠛⡟⠓⠢⠤⢄⠀⠀⠀⠐⠀⠀⠀⠀⡇⣷⡆⠀⠀⠀⠀⠙⣆⠀⠀⠰⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠊⠁⠀⢰⠃⠀⠀⠀⠀⠀⠀⠀⠀⣇⡀⠀⠀⢇⡟⠹⡄⠀⠀⠀⠀⠘⣆⠀⠀⠹⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠐⠁⠀⢀⡴⠋⠀⠀⠀⠀⣼⡇⠀⠀⠀⠀⠀⠀⠀⠀⢹⠁⡀⠀⢸⡇⠀⠘⣦⣀⣀⣀⣀⣘⣆⠀⠀⠹⣷⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⡞⠀⢠⠀⠀⣰⠋⠀⠀⠀⠀⠀⢠⠏⢳⠀⠀⠀⠀⠀⠀⠀⠀⠈⣇⠙⢺⣻⠋⠉⠉⠉⠻⡄⠀⠀⠈⢹⡀⠀⠀⠈⠻⣄⠀⢀⠀⠀⠀⠀\n" +
                "⡇⢀⠆⢀⡜⠁⠀⠀⠀⠀⠀⠀⡞⠀⣨⣶⠀⠀⠀⠀⠀⠀⠀⠀⠸⡄⠀⣿⠀⠀⠀⠀⠀⠈⢢⡀⠀⠘⣧⠀⠀⢠⠀⠈⠳⣌⣳⡀⠀⠀\n" +
                "⣷⠊⣠⠎⠀⠀⠀⠀⠀⠀⢀⣾⡗⠉⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⢹⡀⣹⠀⠀⠀⠀⠀⢀⣀⠈⠢⣀⣻⣆⠀⠸⡄⠀⠀⠈⢫⠙⡶⠀\n" +
                "⡤⣾⠏⠀⠀⠀⠀⠀⢀⡴⢻⡟⠀⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⢻⡟⠀⢄⣤⣶⣶⣿⣷⣶⣶⣿⣿⣿⣆⠀⢷⡀⠀⠀⠀⢣⠙⢆\n" +
                "⡰⠋⠀⠀⠀⠀⠀⠖⠁⢀⠟⠀⠀⣀⠀⢀⡀⠘⢆⠀⠀⠀⠀⠀⠀⠀⠀⣧⠔⠋⠁⣯⣿⣿⣿⣿⣷⠀⠉⣿⡿⢦⡈⣧⡀⠀⠀⠈⢇⠀\n" +
                "⠁⠀⠀⠀⢸⡀⠀⠀⢠⠎⣠⣴⣾⣿⣷⣶⣶⣤⠈⢣⡀⠀⡀⠀⠀⢡⡀⢱⠀⠀⠀⣿⡛⠿⠟⢻⣿⠁⡔⠀⣿⠈⠻⣿⡿⣦⡀⠀⠈⢧\n" +
                "⠀⠀⠀⠀⣸⡇⠀⣠⣿⣾⠟⠁⢸⣽⣿⣿⣿⣿⣇⠀⠙⢤⣳⣤⣀⡀⠙⣤⡇⠀⠀⠈⠛⠑⠂⠈⠉⠁⠀⠀⡿⠀⠀⠈⠳⣿⣿⣷⠖⠤\n" +
                "⠀⠀⠀⣰⢿⠃⣰⣿⠟⠇⠀⠀⠀⢹⡟⠉⣉⡨⠟⠀⠀⠀⠉⠉⠀⠈⠉⠙⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠁⠀⡆⡇⠀⡸⣿⣿⠀⢀\n" +
                "⠀⠀⣰⠃⡾⢾⠉⠈⢧⠀⠑⠂⠤⠴⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⣸⠁⡇⢰⠃⢸⣿⡁⣀\n" +
                "⢀⣴⣿⠊⠀⢸⠀⠀⠀⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⣠⠇⠀⣿⠏⠀⠈⣿⣿⣇\n" +
                "⣿⢻⣿⡆⠀⣾⠀⠀⠀⠀⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣇⣼⠋⠀⠀⡿⠀⠀⠀⢻⢿⣿\n" +
                "⣴⣾⣟⣷⠀⡟⠀⢀⠀⠀⠀⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠛⡽⠁⠀⠀⠀⠁⠀⠀⠀⠸⠙⢧\n" +
                "⠿⠛⠈⡽⠀⡇⠀⠀⡏⠳⣄⠸⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⠈\n" +
                "⣧⠆⡸⠁⠀⡇⠀⢰⠇⠀⠈⠳⣜⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠁⠀⠀⣰⠃⠀⠀⠀⠀⠀⠀⠀⢰\n" +
                "⣿⡾⠁⠀⠀⣷⠀⢸⠀⠀⠀⠀⠈⠛⢧⡍⠒⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠋⡸⠁⠀⢀⣴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⡟⠀⠀⢀⣴⢿⠀⣸⠀⠀⠀⠀⢀⡀⠈⢧⡀⠠⡟⠻⣶⣤⣄⡀⠀⠀⠀⠀⠀⣀⠴⠊⠁⢀⡜⠁⢀⡴⠟⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰\n" +
                "⣄⣠⠞⢉⠏⢸⠀⡿⠀⠀⠀⠀⠀⠈⠳⣤⣙⣦⡇⠀⡇⠈⠙⠛⠻⠶⠶⣶⣿⣧⣤⣤⣶⢋⣠⣶⣯⠀⠀⡞⢇⠀⠀⠀⠀⠀⠀⠀⠀⠈\n" +
                "⠏⠁⢀⠎⠀⢸⡄⡇⠀⠀⢀⠀⡆⠀⠀⡇⠀⢸⠟⢺⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⢛⣉⣉⡉⠁⢸⡇⢠⠇⠘⡆⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠒⠒⠻⠤⣄⣀⣿⣿⠀⠀⡸⠀⢱⠀⠀⡇⠀⢸⡇⢸⠀⠀⠀⠀⠀⠀⠀⠀⣤⡴⠿⠛⠉⠀⠉⣧⡼⠀⡼⠀⠀⠸⡄⠀⢀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠈⢿⢹⠀⢠⠃⠀⠘⡆⠀⡏⠉⣿⣇⡼⠀⠀⠀⠀⣀⡤⠖⠋⠁⠀⠀⠀⠀⠀⠀⣿⡇⣰⠃⠀⠀⠀⢳⠀⢸⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠘⣾⡆⠎⠀⠀⠀⠹⣀⡇⣼⣿⣽⣿⣶⣶⣶⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⣧⣀⡀⠀⠀⠘⡆⢸⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠹⣧⡀⠀⠀⠀⠀⢻⣿⠋⠳⡞⠁⣌⠉⠛⠛⠶⠤⣄⣀⠀⠀⠀⣀⣠⠴⠒⢉⠀⠘⢿⣿⣷⠲⢤⣿⠸⠀⠀⠀⠀⠰";
    }

    public static String mikasaAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣶⣶⣶⣶⣶⣾⣿⣿⣿⣷⣶⣦⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣷⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣼⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⡟⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢰⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣾⣿⣾⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢙⣿⣿⣿⣸⣿⡿⡝⢿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠁⢸⣿⣿⣿⣿⣿⡷⠼⠦⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠧⣄⣸⣿⣿⣿⣿⣿⣷⣶⣶⣮⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣻⣿⣶⣶⣬⣿⣿⣿⣿⣿⡟⠉⣻⣿⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣯⡀⢿⣿⡿⢻⣿⣿⣿⣿⡿⠆⠈⠛⠉⠀⠋⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣇⡉⠀⠉⠁⠈⡿⠿⠿⠏⠀⠀⠀⠀⠀⠀⠀⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⡏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣿⢱⣿⣿⣿⡟⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢸⣿⡟⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠙⠷⣶⡖⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⡇⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢸⢻⣿⣟⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⣠⣬⣧⣤⣄⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣷⣿⡿⣹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢸⡈⢿⣿⣿⣿⣿⣿⣿⣦⣀⠀⠀⠀⠉⣉⡉⠉⠁⠀⠀⠀⠀⢠⣾⡿⣻⣿⣿⣿⣿⣧⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢣⠈⢣⣻⣮⣿⣿⢿⣿⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⣿⢣⣿⠟⢫⣿⣟⠙⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠓⢤⡹⣄⠙⠊⢳⡯⢿⣿⣿⣿⣦⣀⣀⣀⣴⣾⣿⣿⣿⠿⢟⣿⣤⣶⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⣾⣾⣿⣭⣝⣛⣛⣛⣛⣉⣭⣴⣿⣿⣿⣿⡿⠋⣠⣼⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠺⣿⣶⣷⣄⣈⣛⣿⣿⣿⣿⢛⣩⣽⡿⠋⢁⣤⡾⢉⣿⠿⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣵⣾⣿⣟⠻⠿⠿⠥⠾⠶⠾⠛⣋⣡⣴⣾⣿⣯⡾⣹⡿⠀⣼⣿⣻⡶⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢀⣠⢶⣿⡇⢻⣷⣼⣿⠿⣿⡷⠶⣶⣶⠶⢿⣛⣿⣿⠿⣻⡽⢋⠔⢹⠃⢀⣿⣿⣿⡇⠀⣹⡟⣦⣤⣀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣀⡼⠋⣿⣿⣿⡇⢸⣿⠻⣿⣿⣶⣭⡽⠯⠝⡋⣻⣟⣩⡵⠟⣩⠔⠁⢠⡟⠀⣸⣿⣟⣿⠃⠀⣿⢃⣿⡇⠈⠓⠒⠤⣀⠀\n" +
                "⢀⣠⠔⠈⠀⠇⠀⣿⣿⣿⡿⠞⣿⠄⠈⠉⠻⠭⣭⣼⢿⣿⣟⣋⡩⡶⠏⠀⠀⠀⠘⢧⣄⣛⠛⠿⡏⠀⣰⣫⡿⠁⠀⠀⠀⠀⠀⠈⢧";
    }

    public static String lucyAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢒⡖⠤⢤⡀⡀⠂⠀⠀⠐⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡂⠀⡗⠋⠉⠈⠂⠂⠩⢭⡐⠀⠀⠐⠀⠀⠐⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠄⠀⠒⠀⠈⠉⠉⠀⠒⠒⠶⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡀⠀⣀⣠⠶⠖⡩⠀⠈⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡠⠔⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢄⡀⠙⠳⢤⣀⠀⠀⠀⠀⠀⠈⠳⠿⣯⣟⣒⣂⣀⠀⠀⠀⠀⠠⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⣇⠀⠀⠀⠀⠀⢀⣠⠤⠀⠀⠀⢀⣤⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣆⠀⠀⠀⠉⠳⣄⡀⠈⠓⢤⡀⠀⢀⠀⠀⠀⠀⠉⠛⠿⣟⡴⠁⢀⣀⠤⢒⣂⣀⣀\n" +
                "⠀⠀⠀⢸⢻⣦⡀⠀⠀⠘⠋⠀⠀⠀⠀⣠⢟⡵⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡄⠀⠀⠀⠀⠙⢦⣀⠈⠙⣄⠀⠑⢦⡀⠀⠀⠀⠀⣼⠔⣪⣽⡶⠟⠛⠛⠛⠻\n" +
                "⠀⠀⠀⠸⡀⠹⣷⣾⣿⣿⣦⠀⠀⢀⣴⡿⠋⢀⡀⠀⠀⠀⠀⢀⡀⠀⡀⠀⠀⠹⣔⢦⡀⠀⠀⠀⠙⢷⣄⠀⠀⠀⠀⠹⣷⣄⠈⢳⡀⠀⠉⠳⢄⡰⢋⣴⡿⠋⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢷⠀⢸⣿⣿⣿⣿⣷⣶⣿⣿⣠⡾⠇⠀⠀⠀⠀⠀⢸⢸⣄⠙⢦⡀⠀⠈⠳⣝⠲⣄⠀⠀⠀⠙⢷⣄⡀⠀⠀⠈⢯⢦⡀⠱⡄⠀⠀⠀⠳⠟⠛⢫⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⢳⣤⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⡾⠀⠀⣸⢸⠈⠳⣄⠻⢦⡀⠀⠙⢧⣌⠳⢤⡀⠀⠀⠙⢿⣦⣀⠀⠀⢻⡷⡀⠹⡄⠀⠀⠀⠀⠀⠈⠻⡖⠀⢀⠀⠀⠘⡀\n" +
                "⠀⠀⠀⠀⣀⣼⣿⣿⣿⣿⣽⣿⣿⡿⠟⠁⠀⠀⠀⠀⢡⠀⠀⢹⠸⠀⠀⠈⠓⢮⣙⠲⣄⡀⠉⠻⣶⢝⣲⢤⣀⠀⠙⠿⣷⣤⡀⢳⡹⡄⢻⡀⠀⠀⠀⠀⢀⠴⢹⠀⠘⠀⠀⠀⠀\n" +
                "⠀⠀⢠⣾⣿⢿⣿⣿⣿⣿⣿⣿⣿⣷⢤⠀⠀⠀⠀⠀⢸⢸⠀⢸⢧⠀⠀⠀⠀⠀⠈⠑⠦⣽⣲⣤⣈⠳⣍⠙⢺⡭⣶⣦⣬⣙⣿⣦⣷⠱⡀⢧⠀⠤⠔⠒⠊⠀⠀⠆⡀⠀⠀⠀⠀\n" +
                "⠀⣰⣯⠟⠁⢸⡿⠿⣿⣿⣿⣿⣿⣿⣾⡀⠀⠀⠀⠀⠘⡎⡀⢸⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠿⠶⠯⢿⣤⣤⣹⢿⠿⣿⣍⠻⣧⠻⡸⣆⠀⠀⠀⠀⠀⠀⣴⠇⠀⠀⠀⠀\n" +
                "⢰⣿⠇⢀⡤⣾⢣⠄⢿⢏⣿⣿⠉⠘⢿⡇⠀⠀⠀⠀⠀⢿⠇⠘⣿⣠⠴⠒⠛⠛⠛⠒⠶⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⢸⡀⣿⣿⣆⢹⣧⠃⢿⠀⠀⠀⠀⠀⣰⣧⠀⠀⠀⠀⠀\n" +
                "⣸⠇⣠⠊⠀⡿⡼⠀⢸⠘⣿⣷⢷⡀⠀⢻⡀⠀⠀⠀⠀⠘⣾⠀⣿⠁⠀⠀⢠⡄⢀⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠋⣿⠀⠸⣿⢿⣿⣿⡄⣿⡆⠘⡆⠀⠀⠀⢰⢟⡆⠀⠀⠀⠀⠀\n" +
                "⣿⡼⠃⠀⢰⢣⠇⠀⣾⡆⠈⢻⢻⣷⡀⠈⣧⠀⠀⠀⠀⠀⢣⣧⣿⡀⠀⣦⡾⢳⠛⢷⣾⣭⣓⠀⠀⠀⠀⠀⠀⠀⠙⠀⠀⠹⣧⣅⣿⡇⠀⠸⡄⣇⠀⠀⢠⣿⣾⠀⠀⠀⠀⠀⠀\n" +
                "⢿⠃⠀⢀⣯⠟⢠⠀⢸⣿⡄⠈⣦⠙⢝⢦⡘⡆⠀⠀⠀⠀⠘⡞⣼⣯⡿⠋⠀⣿⠀⢸⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠋⠀⢀⡴⢿⣸⠀⢀⣞⣹⠗⠀⠀⠀⠀⠀⠀\n" +
                "⠘⠀⢀⣾⡏⣠⢿⠀⠘⣇⡟⢦⡘⢇⠀⠑⠙⢿⡄⠀⠀⠀⠀⢻⣻⣿⡇⠀⠀⢸⣿⡿⣿⡟⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠒⣻⢻⡇⢞⡿⡖⠉⠀⠀⠀⠀⠀⠀\n" +
                "⠀⢀⣾⣿⠞⠁⢸⡄⠀⢻⣧⠀⠙⠺⢷⡀⠀⠀⣹⡄⠀⠀⠀⠀⢧⣿⣷⡀⠀⠀⢻⣿⣉⢁⣼⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⠈⡇⠈⠀⠙⣄⡀⠀⠀⠀⠀⠀\n" +
                "⠤⠞⠋⠀⠀⠀⠀⢷⠀⠘⣿⡆⠀⠀⠀⠙⢦⣸⠁⢹⣆⠀⠀⠀⠘⣾⣿⣷⣄⠀⠀⠙⠿⠿⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⠄⠀⢀⣴⠋⠀⠀⡇⠀⢀⣴⠋⢳⡀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠈⢧⡀⠙⢿⡄⠀⠀⠀⠈⢿⣧⠸⣟⣆⠀⠀⠀⠈⣿⡍⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠃⢀⣴⢾⣿⠀⠀⠀⢧⠀⠈⣩⢷⡀⢳⡀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⡀⠻⣆⡀⠀⠀⠀⢹⣷⣌⠛⢷⡀⠀⠀⠘⢿⣖⢊⡷⠀⠀⠀⠀⠰⢶⣤⣀⣀⣀⣠⡴⠞⢉⣠⠶⢻⠀⣼⣿⡆⠀⠀⢸⠀⢸⣙⣀⠙⠄⠳⡀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠒⠾⠟⠶⠤⠀⠀⢳⡌⠙⡗⠻⣆⠀⠀⠈⢿⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⠞⠉⢹⠀⢸⠀⡏⢮⢿⡀⠀⢸⠀⠰⣹⣷⡷⠀⠀⠡⡀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠷⠦⠤⠤⡄⠀⠀⠀⠀⠀⢣⠀⢣⠀⢻⠻⣄⠀⠈⢻⣿⠻⣶⣲⡖⠚⣿⠛⠛⠛⠛⢻⡜⢧⣠⡞⠀⢸⢠⡇⠈⢯⣷⡀⢸⠈⠳⣻⣿⣷⣆⠀⠈⢣⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢸⠇⠀⠀⠀⠀⠙⠦⣀⡀⠀⠀⠘⡆⠸⣦⠘⡆⠈⢑⣦⡀⠻⣧⠼⢿⡇⠀⠈⠳⣄⠀⠀⠈⡇⠀⢻⣧⡤⣿⣸⡁⠀⠤⠻⣷⣌⡇⠈⠙⢄⠿⣿⢄⡀⠈⢇\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⡯⠀⠀⠀⠀⠀⠀⠀⠀⠉⠓⢲⡀⣿⠀⢿⡆⢹⣼⠋⠉⠙⢶⣌⢳⡌⣿⠀⠀⢠⣾⢿⣦⣴⣷⣦⡈⢻⣧⠀⠉⢹⡀⢈⡆⡈⠻⣧⠀⠀⣺⣾⡷⠋⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣼⡵⠀⣀⣴⣿⠀⣠⣤⣀⢰⣧⣦⣀⣿⢸⣎⢿⣄⢿⠀⠀⠀⠈⢿⣷⠾⢿⡆⢠⡞⠁⠀⠹⣿⣿⡍⠙⠾⣿⣆⠀⢠⡇⠈⡇⠀⠀⠈⣁⣾⡽⠋⠀⠀⠀⠀⢢\n" +
                "⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣥⣿⣿⣿⡟⠏⠀⠀⣏⡏⠙⣎⢿⣾⡀⠀⠀⠀⢸⣿⡇⠈⣿⠟⠆⠀⠀⠀⠀⠻⣿⣆⠀⠀⠉⢦⣼⠀⠀⠒⢶⡶⠛⣇⠊⠀⠀⠀⠀⠀⠀⠚\n" +
                "⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠉⠀⠀⠀⣸⠟⠀⣄⠈⠳⣿⡇⠀⠀⠀⢸⣿⠁⠀⣁⣠⣴⣾⣿⣿⣿⣿⣿⣿⣷⣦⣤⣀⡙⢦⠀⠀⠈⠹⣔⡛⢧⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠂⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣄⠀⡀⠀⠀⠋⠀⠀⠛⠢⣄⠈⡇⠀⠀⠀⢸⣇⣴⣿⣿⣿⣿⠿⠿⠛⠛⠛⠻⢿⣿⣿⣿⣿⣿⣿⡇⠀⣇⣠⣤⠽⢷⡅⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⡇⠀⣀⠀⠀⠀⠀⠈⠀⢳⠀⠀⠀⢸⣿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⢀⣿⡏⠀⠈⠉⠛⢿⡇⠀⠁⠀⡇⠀⠀⢃⠀⠀⠀⠀⠀⠐\n" +
                "⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⡀⠀⣀⡀⠀⠀⢸⠀⠀⠀⢸⠘⡆⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠟⠀⠀⠀⢀⡴⠋⠀⠀⠀⢀⣧⣠⠴⠘⠀⠀⠀⠀⠀⠀\n" +
                "⡂⠀⠀⢘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⣇⠀⠀⢸⠀⠀⠀⣾⠞⠁⠀⠀⣀⣐⣒⡲⠿⢻⣿⠋⠀⠀⣠⣾⠋⠠⠤⢶⠚⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢧⣰⡦⠨⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⢸⠀⣠⠾⠃⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⡟⠀⠀⢀⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⠀";
    }
}
