package MainLogic;

import Data.*;

import java.util.ArrayList;
import java.util.Random;

import QuadTree.Coordinates;

public class CadastralObjectGenerator {

    private Random random;

    public enum GenerateOption {
        LAND_PARCEL,
        REAL_ESTATE,
        BOTH
    }

    public CadastralObjectGenerator() {
        this.random = new Random();
    }

    public ArrayList<CadastralObject> generateObjects(
            GenerateOption parType, int parCount, double parSizeOfObject, GPS[] parRangeOfGPS
    ) {
        ArrayList<CadastralObject> listToReturn = new ArrayList<>();
        if (parType.equals(GenerateOption.LAND_PARCEL)) {
            for (int i = 0; i < parCount; i++) {
                listToReturn.add(
                        new LandParcel("Land_Parcel_" + i, this.returnObject(parSizeOfObject,parRangeOfGPS),i)
                );
            }
        } else if (parType.equals(GenerateOption.REAL_ESTATE)) {
            for (int i = 0; i < parCount; i++) {
                listToReturn.add(
                        new RealEstate("Real_Estate_" + i, this.returnObject(parSizeOfObject, parRangeOfGPS), i)
                );
            }
        } else {
            for (int i = 0; i < parCount; i++) {
                double objectProbability = random.nextDouble();
                if (objectProbability > 0.5) {
                    listToReturn.add(
                            new LandParcel("Land_Parcel_" + i,this.returnObject(parSizeOfObject,parRangeOfGPS),i)
                    );
                } else {
                    listToReturn.add(
                            new RealEstate("Real_Estate_" + i, this.returnObject(parSizeOfObject, parRangeOfGPS), i)
                    );
                }
            }

        }
        return listToReturn;
    }

    private GPS[] returnObject(double parSizeOfObject, GPS[] parRangeOfGPS) {
        MapCoordinates mp = new MapCoordinates(parRangeOfGPS);
        Coordinates coors = mp.getCoordinatesOfRoot();

        double sizeOfXAxes = coors.getUpperX() - coors.getLowerX();
        double sizeOfYAxes = coors.getUpperY() - coors.getLowerY();

        double x1 = random.nextDouble() * (sizeOfXAxes - parSizeOfObject - 1);
        double x2 = x1 + parSizeOfObject;
        double y1 = random.nextDouble() * (sizeOfYAxes - parSizeOfObject - 1);
        double y2 = y1 + parSizeOfObject;

        Coordinates coor = new Coordinates(x1,x2,y1,y2);

        GPS[] gps = mp.getGPSValue(coor);

        return gps;
    }
}
