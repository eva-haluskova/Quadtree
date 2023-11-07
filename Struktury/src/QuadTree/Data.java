package QuadTree;

import java.util.UUID;

/**
 * Represent any data, which can be inserted into quad tree.
 * Because data inserted into quad tree needs to have coordinates,
 * this class holds data of type T - so we can put there anything,
 * and at the same time class contains coordinates of data ensuring every
 * data which we want to insert into quadtree has coordinates.
 */

// TODO potrebujes zabezpecit enkapuslaciu.
// Asi to neni koser si myslim. Akoze tie gettre...minimalne tie cooridantes

public class Data<T>
{
    private T data;
    //private final int id;
    private int id;
    private Coordinates coordinates;

    public Data(T parData, Coordinates parCoordinates, int parId) {
        this.data = parData;
        this.coordinates = parCoordinates;
        this.id = parId;
    }

    public Data(T parData, Coordinates parCoordinates) {
        this.data = parData;
        this.coordinates = parCoordinates;
    }

    public T getData() {
        return this.data;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public int getId() {
        return this.id;
    }
}
