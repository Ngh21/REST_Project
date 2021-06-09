import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class Data_Servlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String requestUrl = req.getRequestURI();
        String name = requestUrl.substring("/people/".length());

        Person person = People_Data.getInstance().getPerson(name);

        if(person != null && person.isGrad()) {
            String json = "{\n";
            json += "\"name\": " + JSONObject.quote(person.getName()) + ",\n";
            json += "\"age\": " + person.getAge() + ",\n";
            json += "\"graduated\": " + person.isGrad() + "\n";
            json += "\"id\": " + person.getId() + ",\n";
            json += "}";
            res.getOutputStream().println(json); 
        } else if (person != null && !person.isGrad()) {
            String json = "{\n";
            json += "\"name\": " + JSONObject.quote(person.getName()) + ",\n";
            json += "\"age\": " + person.getAge() + ",\n";
            json += "\"graduated\": " + person.isGrad() + ",\n";
            json += "\"id\": " + person.getId() + ",\n";
            json += "}";
            res.getOutputStream().println(json);
        } else {
            res.getOutputStream().println("{}");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        boolean graduated = Boolean.parseBoolean(req.getParameter("graduated"));
        int id = Integer.parseInt(req.getParameter("id"));
        
        People_Data.getInstance().putPerson(new Person(name, age, graduated, id));
    }
}