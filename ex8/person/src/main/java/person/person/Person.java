package person.person;

public class Person {

    private Integer personId;
    private String name;
    private Integer age;

    public Person (String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public Integer getPersonId() {
        return personId;
    }

    public void setId(Integer personId) {
        this.personId = personId;
    }
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}


}
