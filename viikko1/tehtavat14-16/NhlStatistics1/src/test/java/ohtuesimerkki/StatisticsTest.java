package ohtuesimerkki;
import java.util.List;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void searchPalauttaaPelaajanJosNimiLoytyy(){
        Player p = stats.search("Lemieux");
        assertEquals("Lemieux",p.getName());
    }

    @Test
    public void searchPalauttaaTyhjanJosNimeaEiLoydy(){
        Player p = stats.search("Tyhja");
        assertEquals(null,p);
    }

    @Test
    public void teamLoytaaSamanJoukkueenPelaajat(){
        List<Player> team = stats.team("PIT");
        assertEquals("Lemieux",team.get(0).getName());
        assertEquals(1,team.size());
    }

    @Test
    public void topScorersPalauttaaPeaaajatOikeassaJarjestyksessa(){
        List<Player> team = stats.topScorers(3);
        assertEquals("Gretzky",team.get(0).getName());
        assertEquals("Lemieux",team.get(1).getName());
        assertEquals("Yzerman",team.get(2).getName());
    }
}
