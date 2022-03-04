# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

## Answer

TCC et LCC peuvent être égaux si :  
* Les méthodes ne partagent aucuns paramètes communs, alors TCC = LCC = 0
* Les méthodes ont toutes les mêmes paramètres, alors TCC = LCC = nombre de méthodes de la classe

Par exemple, la classe suivante : 

```java
class Figure {
    private int hauteur;
    private int longueur;
    private int largeur;
  
    public Figure(int hauteur, int longueur, int largeur) {
        this.hauteur = hauteur;
        this.longueur = longueur;
        this.largeur = largeur;
    }
  
    public int egale(Figure fig) {
        return longueur - fig.longueur && largeur - fig.largeur && hauteur - fig.hauteur;
    }
    
    public int calculeVolume(){
        return hauteur * longueur * largeur;
    }
  
    public void affiche() {
        System.out.println("longueur = "+this.longueur + "; largeur = " +this.largeur + " ; hauteur = " + this.hauteur);
    }
}
```

Les 3 méthodes utilisent des paramètres différents, alors dans le graphe de cohésion de la classe, les noeuds ne sont pas reliés.  
TCC = 0 et LCC = 0

LCC ne peut pas être inférieur à TCC, car les liaison directes sont inclues dans les liaisons indirectes.  
LCC >= TCC
