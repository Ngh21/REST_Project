public class Person {
    private String name;
    private int age;
    private boolean graduated;
    private int id;

    public Person(String name, int age, boolean graduated, int id) {
        this.name = name;
        this.age = age;
        this.graduated = graduated;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public boolean isGrad(){
        return graduated;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        if(graduated)
            return name + " is a person and is " + age + " years old. " + "They have graduated and have an ID number of " + id;
        else
            return name + " is a person and is " + age + " years old. " + "They have not graduated and have an ID number of " + id;
    }

}