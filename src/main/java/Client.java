import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private Integer age;
    private String appearance;
    private String neighbourhood;
    public int id;
    private static List<Client> instances = new ArrayList<Client>();

    public Client(String name, Integer age, String firstAppearance, String neighbourhood){
        mName =name;
        mAge = age;
        mFirstAppearance = firstAppearance;
        mNeighbourhood = neighbourhood;
        instances.add(this);
        mId = instances.size();
    }

    public static List<Client> all(){

        return instances;
    }

    public int getId(){

        return mId;
    }

    public static Client find(int id){

        return instances.get(id - 1);
    }

    public String getName(){

        return mName;
    }
    public Integer getAge(){

        return mAge;
    }
    public String getFirstAppearance(){

        return mFirstAppearance;
    }
    public String getNeighbourhood(){

        return mNeighbourhood;
    }
}
