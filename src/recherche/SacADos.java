package recherche;

import java.io.*;
import java.util.Random;

public class SacADos {

    private final static String FILE_NAME = "test.csv";
    public static int nbLigne = 4; // Nombre de lignes dans le fichier
    String[] tab = new String[nbLigne];
    String[] tab2, tab3;
    String ligne, chaine = "";
    int i = 0;
    Integer nbItem;
    int[] profit, poids;
    private Integer poidsTot;
    private Integer[] sol1;
//    private Integer sol2[] = new Integer[10];
    private double sommeProfit = 0;
    private double sommePoids = 0;
    private double resultat;
    private double rapport;

    private double beta;
  
    
    public int nbEval = 100;

    public SacADos(String fichier) {

        lectureFichier(fichier); // Lecture du fichier 
        nbItem = new Integer(tab[0]).intValue();//nombre d'item

        //Recherche Aléatoire 
        sol1 = new Integer[nbItem];
        Random randomGenerator = new Random();
        for (int idx = 0; idx < nbItem; idx++) {
            sol1[idx] = randomGenerator.nextInt(2);
//            System.out.println("Generated : " + sol1[idx]);
        }

        //profits
        tab2 = tab[1].split(" ");
        profit = new int[nbItem];
        //On boucle sur la 2ème ligne du fichier
        for (int i = 0; i < tab2.length; i++) {
            profit[i] = Integer.parseInt(tab2[i]);
            if (sol1[i] == 1) {
                sommeProfit += profit[i];
                //System.out.println("profit: " + profit[i]);  
            }
        }

        //Poid
        sommePoids = 0;
        tab3 = tab[2].split(" ");
        poids = new int[nbItem];
        //On boucle sur la 3ème ligne du fichier
        for (int i = 0; i < tab3.length; i++) {
            poids[i] = Integer.parseInt(tab3[i]);
            if (sol1[i] == 1) {
                sommePoids += poids[i];
                //System.out.println("poids: " + poids[i]);
            }
        }

    }
    
    

    // Calcul du max béta et du résultat
    public void calculBeta() {
        poidsTot = new Integer(tab[3]);
        if (this.sommePoids <= this.poidsTot) {
            this.resultat = this.sommeProfit;
        } else {
            this.beta = 0;
            //On boucle sur la 3ème ligne du fichier
            for (int i = 0; i < this.tab3.length; i++) {
                this.rapport = (double) this.profit[i] / (double) this.poids[i];//On calcule le rapport profi poid
                //On applique la formule
                if (this.rapport > this.beta) {
                    this.beta = this.rapport;
                }
            }
        }
        this.resultat = this.sommeProfit - this.beta * (this.sommePoids - this.poidsTot);

    }

    //Lecture du fichier
    public void lectureFichier(String fichier) {
        try {

            // Pour améliorer tout refaire avec des scanner et nextInt
            /*
             Scanner sc = new Scanner(fichier);

             while (sc.hasNextLine()) {
             int i = sc.nextInt();
             System.out.println(i);
             this.tab[i] = Integer.toString(i);
             i++;
             }
             sc.close();
            
             */
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);

            while ((ligne = br.readLine()) != null) {
                this.tab[i] = ligne;
                System.out.println(ligne);
                this.chaine += this.ligne + "\n";
                i++;
            }

            br.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //Retourne tous les résultats
    public String toString() {
        return "Nombre d'item : " + nbItem + "\nSomme profit : " + this.sommeProfit + "\nSomme Poid : " + this.sommePoids + "\nPoid Total : " + this.poidsTot + "\nBéta : " + this.beta + "\nRésultat : " + this.resultat;
    }

    // écrire profit dans un fichier en norme csv
    public void toCSV(int nbEval) {

        try {
            FileWriter fw = new FileWriter("statRes.csv", true);
            PrintWriter pWriter = new PrintWriter(fw);

            pWriter.print(nbEval + ";" + this.sommeProfit + "\n");

            System.out.println("Le fichier statRes.csv a bien été créé ou édité");
            pWriter.close();
        } catch (IOException err) {
            System.out.println("Erreur : " + err);
        }
    }

    /**
     * GETTER
     */
    public double getSommeProfit() {
        return this.sommeProfit;
    }

    public double getSommePoid() {
        return this.sommePoids;
    }

    public int getPoidTot() {
        return this.poidsTot.intValue();
    }

    public double getResultat() {
        return this.resultat;
    }

    public double getBeta() {
        return this.beta;
    }

}
