import java.util.HashSet;
import java.util.Set;

public class Librairie {
    private final Set<Magasin> magasins;

    public Librairie() {
        this.magasins = new HashSet<>();
    }

    public void ajouteMagasins(Magasin magasin) {
        this.magasins.add(magasin);
    }

    @Override
    public String toString() {
        String res = "";
        for (Magasin magasin : magasins) {
            res += magasin.toString();
        }
        return res;
    }

}
