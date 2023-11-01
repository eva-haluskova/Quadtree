import QuadTree.QuadTree;
import QuadTree.Place;
import QuadTree.Coordinates;
import QuadTree.Tester.TesterForPlace;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int velkost = 100000;
        Random random = new Random();
        random.setSeed(30);
        QuadTree<Place> testTree = new QuadTree<Place>(0, velkost, 0, velkost,6);

        Coordinates coorsOfSearch = new Coordinates(30,60,30,80);
        TesterForPlace placeTester = new TesterForPlace(testTree);
        //placeTester.testOfSeed(4,100000,coorsOfSearch);
        //placeTester.testForSeed(2,20,coorsOfSearch);

        //placeTester.generateInsert(50000,random,coorsOfSearch);

        placeTester.levelOrderTraversalWithNodeNumbers();

        placeTester.testOfChangeOfDepth(10,100, coorsOfSearch);

        placeTester.levelOrderTraversalWithNodeNumbers();

        System.out.println("Zatial to funguje ^-^");
    }
}






// TODO vdase pozjednodusovat vyrazy list.get(0). a zaroevn list.remove(0)....da sa to, vis?
// TODO over si ci to doublevanie ma zmysel pri < >
// TODO over si ci niekde neporusujes zapuzdrenie, getovanie zoznamov cez kopiu!!!
// TODO with index access method pay attention if index exists?
// TODO zaujimavy poznaok o zmene velkosti ako ked som zvacsila velkost sa mi pekne krasne zvysila vyska stromu pri vlastne 10satine dat...teda dat na desasitine velkosti
// TODO MUST!!! change your arrays at least to linked list or sometnig more efficient!!!!
// TODO pri mazanai aj znizovanie stupna stromu?
// TODO think about if is needed in method findAppropriateNode that one if in case current isnt leaf.!
// TODO pripadne si refactorni insert na krajsi
// TODO returvnovanie tych onych. Porusenie zapuzdrenia? (DUfam ze nie)(salala)RESULT
// TODO dories ten generator poondiaty
// TODO refactorni si cooridnates a s tym vsetky suvisiace metody ktore mas vsade po triedach...to tam nema co robit, ked to porovnana iba dooridnates!!!
// TODO refactorni cely node, isto vela sa da pomazat a dat do coorinates a tak
// TODO ZAPUZDRENIE!!!
// TODO sorry jako, ale v delete nemas osetrenu zmenu hlbky stromu..teda akoze iba aktualizovanie ciselok, vis, ked tak potom oprav aj ten test na to
// TODO tu zmenu vysky zvas si to ukladanie levelu nodu!! ulahcilo by ti to zivot, dobra rada nad zlato to je!!!