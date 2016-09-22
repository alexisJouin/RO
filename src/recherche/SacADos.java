package recherche;

import java.io.*;
import static java.rmi.server.LogStream.log;
import java.util.Random;
import java.util.Scanner;

public class SacADos {

    String[] tab = new String[4];
    String[] tab2, tab3;
    String ligne, chaine = "";
    int i = 0;
    Integer nbItem;
    Integer[] profit = new Integer[5];
    Integer[] poids = new Integer[5];
    private Integer poidsTot;
    boolean sol1[] = {false, false, true, true, false};
    private double sommeProfit = 0;
    private double sommePoids = 0;
    private double resultat;
    private double rapport;
    //private poidTot = new Integer(tab[3]);

    private double beta;

    public SacADos(String fichier) {

        lectureFichier(fichier);

        //Affichage du nombre d'item possible d'emporter
        System.out.println("");
        nbItem = new Integer(tab[0]);
        System.out.println("nbItem: " + nbItem.intValue());

        //Affichage des profits pour chaque items proposés
        System.out.println("");
        tab2 = tab[1].split(" ");
        for (int comp = 0; comp < tab2.length; comp++) {
            profit[comp] = Integer.parseInt(tab2[comp]);
            if (sol1[comp] == true) {
                sommeProfit += profit[comp];
                System.out.println("profit: " + profit[comp]);
            }
        }

        //Affichage des poids pour chaque items proposés
        sommePoids = 0;
        System.out.println("");
        tab3 = tab[2].split(" ");
        for (int comp = 0; comp < tab3.length; comp++) {
            poids[comp] = Integer.parseInt(tab3[comp]);
            if (sol1[comp] == true) {
                sommePoids += poids[comp];
                System.out.println("poids: " + poids[comp]);
            }
        }

        Random randomGenerator = new Random();
        for (int idx = 1; idx <= 10; ++idx) {
            int randomInt = randomGenerator.nextInt(100);
            System.out.println("Generated : " + randomInt);
        }

    }

    // Calcul du max béta et du résultat
    public void calculBeta() {
        poidsTot = new Integer(tab[3]);
        if (this.sommePoids <= this.poidsTot) {
            beta = this.sommeProfit;
        } else {
            this.beta = 0;
            for (int i = 0; i < this.tab3.length; i++) {
                this.rapport = (double) this.profit[i] / (double) this.poids[i];
                if (this.rapport > this.beta) {
                    this.beta = this.rapport;
                }
            }
        }
        this.resultat
                = this.sommeProfit - this.beta * (this.sommePoids - this.poidsTot);
    }

    //Lecture du fichier
    public void lectureFichier(String fichier) {
        try {
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

    
    public String toString(){
        return "Somme profit : "+this.sommeProfit+"\nSomme Poid : "+this.sommePoids+"\nPoid Total : "+this.poidsTot+"\nBéta : "+this.beta+"\nRésultat : "+this.resultat ;
    }
    /**
     * GETTER
     */
    public double getSommeProfit() {
        return sommeProfit;
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
