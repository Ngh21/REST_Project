import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import org.json.JSONException;
import org.json.JSONObject;

public class RestAPI_Client {

    public static void main (String[] args) throws IOException, JSONException{

        Scanner scanner = new Scanner(System.in);

        System.out.println("Personal information data system");
        System.out.println("You can either \"Get\" a persons data or \"Update\" a users data, which would you like to do?");
        String action = scanner.nextLine();
        if(action.equalsIgnoreCase("get")) {
            System.out.println("Whose data would you like to retrieve");
            String name = scanner.nextLine();

            String jsonString = getPersonData(name);
            JSONObject jsonObject = new JSONObject(jsonString);

            int age = jsonObject.getInt("age");
            boolean graduated = jsonObject.getBoolean("graduated");
            int id = jsonObject.getInt("id");

            Person newPerson = new Person(name, age, graduated, id);
            System.out.println(newPerson.toString());
        } else if (action.equalsIgnoreCase("update")) {
            System.out.println("Whose data would you like to update?");
            String name = scanner.nextLine();
            System.out.println("What is " + name + "'s age?");
            int newAge = scanner.nextInt();
            System.out.println("Has " + name + " graduated?");
            
        }
    }

    public static String getPersonData(String name) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/people/" + name).openConnection();

        connection.setRequestMethod("GET");

        int resCode = connection.getResponseCode();
        if(resCode == 200) {
            String res = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                res += scanner.nextLine();
                res += "\n";
            }
            scanner.close();

            return res;
        }
        
        return null;
    }

    public static void setPersonData(String name, int age, boolean graduated, int id) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/people/" + name).openConnection();

        connection.setRequestMethod("POST");
        String postData = "name=" + URLEncoder.encode(name);
        postData += "&age=" + age;
        postData += "&graduated=" + graduated;
        postData += "&id=" + id;

        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(postData);
        writer.flush();

        int resCode = connection.getResponseCode();

        if(resCode == 200) {
            System.out.println("Successfully updated " + name);
        } else 
            System.out.println("Unable to update");
    }
}