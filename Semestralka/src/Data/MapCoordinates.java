package Data;

import QuadTree.Coordinates;

public class MapCoordinates {

   private GPS[] gpsOfRoot;
   private Coordinates coordinatesOfRoot;

   public MapCoordinates(GPS[] parGpsOfRoot) {
      if (parGpsOfRoot[0].isBiggerThan(parGpsOfRoot[1]) == -1) {
         this.gpsOfRoot = parGpsOfRoot;
      } else {
         GPS one = parGpsOfRoot[1];
         GPS two = parGpsOfRoot[0];
         GPS[] ofRoot = {one, two};
         this.gpsOfRoot = ofRoot;
      }
      this.coordinatesOfRoot = getCoordinatesValue(parGpsOfRoot[0], parGpsOfRoot[1]);
   }

   public Coordinates getCoordinatesValue(GPS parGPSOne, GPS parGPSTwo) {
      double width, height;
      double posX, posY;

      GPS[] parGps;
      if (parGPSOne.isBiggerThan(parGPSTwo) == -1) {
         parGps = new GPS[]{parGPSOne, parGPSTwo};
      } else {
         parGps = new GPS[]{parGPSTwo, parGPSOne};
      }

      double[] hWBetweenPoints = this.getWidthHeightBetweenTwoGPS(parGPSOne, parGPSTwo);
      double[] hWBetweenRootAndPoint;

      hWBetweenRootAndPoint = this.getWidthHeightBetweenTwoGPS(parGps[0], this.gpsOfRoot[0]);

      height = hWBetweenPoints[1];
      width = hWBetweenPoints[0];
      if (this.coordinatesOfRoot != null) {
         posX = this.coordinatesOfRoot.getLowerX() + hWBetweenRootAndPoint[0];
         posY = this.coordinatesOfRoot.getLowerY() + hWBetweenRootAndPoint[1];
      } else {
         posX = 0;
         posY = 0;
      }

      return new Coordinates(posX,posX + width,posY,posY + height);
   }

   /**
    * return width and height of difference between two GPS coordinates
    * @return int[0] - width [x]
    *         int[1] - height [y]
    *
    */
   public double[] getWidthHeightBetweenTwoGPS(GPS parGPSOne, GPS parGPSTwo) {
      double[] dimension = new double[2];

      if (parGPSOne.getLatitude() == parGPSTwo.getLatitude()) {
         dimension[1] = Math.abs(parGPSOne.getLatitudePosition() - parGPSTwo.getLatitudePosition());
        // dimension[1] = parGPSOne.getLatitudePosition() - parGPSTwo.getLatitudePosition();

      } else if (parGPSOne.getLatitude() != parGPSTwo.getLatitude()) {
         dimension[1] = Math.abs(parGPSOne.getLatitudePosition() + parGPSTwo.getLatitudePosition());
         //dimension[1] = parGPSOne.getLatitudePosition() + parGPSTwo.getLatitudePosition();
      }

      if (parGPSOne.getLongitude() == parGPSTwo.getLongitude()) {
         dimension[0] = Math.abs(parGPSOne.getLongitudePosition() - parGPSTwo.getLongitudePosition());
         //dimension[0] = parGPSOne.getLongitudePosition() - parGPSTwo.getLongitudePosition();
      } else if (parGPSOne.getLongitude() != parGPSTwo.getLongitude()) {
        dimension[0] = Math.abs(parGPSOne.getLongitudePosition() + parGPSTwo.getLongitudePosition());
        // dimension[0] = parGPSOne.getLongitudePosition() + parGPSTwo.getLongitudePosition();
      }

      return dimension;
   }

   public Coordinates getCoordinatesOfRoot() {
      return this.coordinatesOfRoot;
   }

   public GPS[] getGpsOfRoot() {
      return this.gpsOfRoot;
   }
}
