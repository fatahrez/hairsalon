import java.util.List;

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
    public String getClientStyle(){

        return style;
    }
    public int getStylistId(){

        return stylistId;
    }
    public static List<Client> all() {
        String sql = "SELECT id, name, stylistId FROM clients";
           try(Connection con = DB.sql2o.open()) {
           return con.createQuery(sql).executeAndFetch(Client.class);
        }
     }

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
            String sql = "INSERT INTO clients(name, style, stylistId) VALUES (:name, :style, :stylistId)";
            this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("style", this.style)
            .addParameter("stylistId", this.stylistId)
            .executeUpdate()
            .getKey();
        }
    }

    public void update(String name, String style){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE clients SET (name, style) = (:name, :style) WHERE id = :id;";
            con.createQuery(sql)
            .addParameter("name", name)
            .addParameter("style", style)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public static void clear(){
        String sql = "DELETE FROM clients *";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }


}
