package QuadTree;

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

        return 0;
    }
}
