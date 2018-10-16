import static  org.junit.Assert.*;
import  org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


public class ClientTest {
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/my_salon_test", "fatah", "6674fatah");
    }
    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM clients *;";
            con.createQuery(sql).executeUpdate();
        }
    }
    @Test
    public void clientReturnsAnInstanceOfObject_object(){
        Client client = new Client("hll","hjjn",1);
        assertTrue(client instanceof Object);
    }
    @Test
    public void getNameOfClient_String(){
        Client client = new Client("hll","hjjn",1);
        assertEquals("hll", client.getClientName());
    }

    @Test
    public void returnsClientsAsInstanceOfClientClass_Client(){
        Client client = new Client("hll","hjjn",1);
        Client client2 = new Client("dhkj","dhjhj",1);

        assertTrue(Client.all().contains(client));
        assertTrue(Client.all().contains(client2));
    }
}
