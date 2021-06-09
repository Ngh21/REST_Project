import java.util.HashMap; 
import java.util.Map;

public class People_Data{
    private Map<String, Person> people = new HashMap<>();

    private static People_Data allPeople = new People_Data();
    public static People_Data getInstance() {
        return allPeople;
    }

    private People_Data(){
        people.put(null, null);
    }

    public Person getPerson(String name) {
        return people.get(name);
    }

    public void putPerson(Person person){
        people.put(person.getName(), person);
    }

}