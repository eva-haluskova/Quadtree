package QuadTree;

/**
 * Basic class mainly for testing how quad tree implementation works.
 */
public class Place implements Comparable<Place> {

    private String name;

    public Place(String parName) {
        this.name = parName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Place parOtherPlace) {
        if (this.name.equals(parOtherPlace.getName())) {
            return 1;
        } else {
            return -1;
        }
    }
}
