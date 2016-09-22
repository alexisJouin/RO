package recherche;

/**
 *
 * @author Alexis
 */
public class Recherche {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SacADos s1 = new SacADos("donnees.txt");
        s1.calculBeta();
        
//        System.out.println("Somme profit: " + s1.getSommeProfit());
//        System.out.println("Somme poids: " + s1.getSommePoid());
//        System.out.println("Poids Total: " + s1.getPoidTot());
//        System.out.println("Beta = " + s1.getBeta());
//        System.out.println("Résultat = " + s1.getResultat());
//        
        System.out.println(s1.toString());
        
        SacADos s2 = new SacADos("ks_1000.dat");
        s2.calculBeta();
        System.out.println("Somme profit: " + s2.getSommeProfit());
    }

}
