import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,(new Rating(item,rating)));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);

    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }

        return list;
    }
    public String toString () {
        String result = "Rater [id:" + myID + "|Ratings:" ;
        for(int k=0; k < myRatings.size(); k++){
            result += "(" +(myRatings.get(k).getItem()) + "," + myRatings.get(k).getValue() + ")";

        }
        result+= "]";
        return result;
    }
}
