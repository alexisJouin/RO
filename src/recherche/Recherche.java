package recherche;

/**
 *
 * @author Alexis
 */
public class Recherche {

    private static int nbEval;

    public static void main(String[] args) {

        //Pour la recherche Aléatoires
        //Pour la boucle pour générer le fichier csv
        int max = 100000;
        nbEval = 100;
        //On boucle pour remplir le csv
        while (nbEval <= max) {
            SacADos s1 = new SacADos("ks_1000.dat");

            s1.calculBeta();

//        System.out.println("Somme profit: " + s1.getSommeProfit());
//        System.out.println("Somme poids: " + s1.getSommePoid());
//        System.out.println("Poids Total: " + s1.getPoidTot());
//        System.out.println("Beta = " + s1.getBeta());
//        System.out.println("Résultat = " + s1.getResultat());
//        
            System.out.println(s1.toString()); //Affiche toutes les infos

            s1.toCSV(nbEval);//Ecrit dans un fichier csv le profit / aux nombre d'évaluation
            nbEval += 100; //Pas de 100 pour la boucle
        }

        //Pour la marche aléatoire
        while (nbEval <= max) {
            SacADos s2 = new SacADos("ks_1000.dat");
            s2.calculBeta();
            s2.marcheAleatoire(nbEval);
            nbEval += 100; //Pas de 100 pour la boucle
        }

    }

}
