import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Client {
    
    private int id;
    private String name;
    private String style;
    private int stylistId;

    public Client(String name, String style, int stylistId){
        this.name =name;
        this.style = style;
        this.stylistId = stylistId;
    }

    public int getId(){

        return id;
    }

    public String getName(){

        return name;
    }
    public Integer getAge(){

        return age;
    }
    public String getFirstAppearance(){

        return firstappearance;
    }
    public String getNeighbourhood(){

        return neighbourhood;
    }

    public static List<Client> all() {
        String sql = "SELECT id, name, firstappearance FROM clients";
           try(Connection con = DB.sql2o.open()) {
           return con.createQuery(sql).executeAndFetch(Client.class);
        }
     }

    // public static Client find(int id){
    //     try(Connection con = DB.sql2o.open()){
    //         String sql = "SELECT * FROM clients where id =:id";
    //         Client client = con.createQuery(sql)
    //         .addParameter("id", id)
    //         .executeAndFetchFirst(Client.class);
    //         return client;
    //     }
    // }

    public static Client find(int id) {
        try(Connection con = DB.sql2o.open()) {
           String sql = "SELECT * FROM clients where id=:id";
           Client client = con.createQuery(sql)
           .addParameter("id", id)
           .executeAndFetchFirst(Client.class);
           return client;
        }
     }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO clients(name, age, firstappearance, neighbourhood) VALUES (:name, :age, :firstappearance, :neighbourhood)";
            this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("age", this.age)
            .addParameter("firstappearance", this.firstappearance)
            .addParameter("neighbourhood", this.neighbourhood)
            .executeUpdate()
            .getKey();
        }
    }
}
