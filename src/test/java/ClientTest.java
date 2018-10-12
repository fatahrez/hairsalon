import static  org.junit.Assert.*;
import  org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


public class ClientTest {
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "fatah", "6674fatah");
    }
    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM client *;";
            con.createQuery(sql).executeUpdate();
        }
    }
    @Test
    public void clientReturnsAnInstanceOfObject_object(){
        Client client = new Client("shiro", 20, "may", "koma");
        assertTrue(client instanceof Object);
    }
    @Test
    public void getNameOfClient_String(){
        Client client = new Client("shiro", 20, "may", "koma");
        assertEquals("shiro", client.getName());
    }
    @Test
    public void getAgeOfClient_int(){
        Client client = new Client("shiro", 20, "may", "koma");
        Integer age = 20;
        assertEquals(age, client.getAge());
    }
    @Test
    public void getFirstApperanceofClient_string(){
        Client client = new Client("shiro", 20, "may", "koma");
        assertEquals("may", client.getFirstAppearance());
    }
    @Test
    public void getneigghbourhood_string(){
        Client client = new Client("shiro", 20, "may", "koma");
        assertEquals("koma", client.getNeighbourhood());
    }
    @Test
    public void returnsClientsAsInstanceOfClientClass_Client(){
        Client client = new Client("shiro", 20, "may", "koma");
        Client client2 = new Client("epha", 20, "may", "koma");

        assertTrue(Client.all().contains(client));
        assertTrue(Client.all().contains(client2));
    }
}
