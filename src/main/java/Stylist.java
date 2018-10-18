import java.util.List;
import org.sql2o.Connection;
public class Stylist {
  private int id;
  private String name;
  private String style;

  public Stylist(String name, String style){
    this.name = name;
    this.style = style; 
  }

  public String getStylistName(){
    return name;
  }
  public String getStyle(){
    return style;
  }
  public int getId(){
    return id;
  }

  public static List<Stylist> allStylist(){
    String sql = "SELECT * FROM stylist;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  } 
  public List<Client> getClients(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients where stylistid=:id";
      return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Client.class);
    }
  }

  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylist where id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylist (name, style) VALUES (:name, :style)";
      this.id= (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("style", this.style)
      .executeUpdate()
      .getKey();
    }
  }

  public void update(String name, String style){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE stylist SET (name, style) = (:name, :style) WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("style", style)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM stylist WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if(!(otherStylist instanceof Stylist)){
      return false;
    }
    else{
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylistName().equals(newStylist.getStylistName());
    }
  }
}
