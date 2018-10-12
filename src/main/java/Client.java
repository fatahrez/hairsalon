import java.util.ArrayList;
import java.util.List;

public class Client {
    private String mName;
    private Integer mAge;
    private String mFirstAppearance;
    private String mNeighbourhood;
    public int mId;
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
