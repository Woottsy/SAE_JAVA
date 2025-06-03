import java.util.Set;
import java.util.HashSet;
public class Librairie {
    private Set<Magasin> magasins;
    public Librairie(){
        this.magasins=new HashSet<>();
    }
    public void ajouteMagasins(Magasin magasin){
        this.magasins.add(magasin);
    }
    public String toString(){
        String res="";
        for (Magasin magasin : magasins) {
            res+=magasin.toString();
        }
        return res;
    }
    
}
