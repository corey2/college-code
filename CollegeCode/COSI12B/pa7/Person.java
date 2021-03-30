package pa7;

import java.util.*;


class Person {
    private String name;
    private Person mother, father;
    private ArrayList<Person> children;

    public String toString(){
	return getName();
    }

    public Person(String nm){
	name = nm;
	mother = null;
	father = null;
	children = new ArrayList<Person>();
    }

    public String getName(){
	return name;
    }
    
    public Person getMother(){
	return mother;
    }

    public Person getFather(){
	return father;
    }

    public ArrayList<Person> getChildren(){
	return children;
    }
    
    public void setMother(Person m){
	mother = m;
    }
    
    public void setFather(Person f){
	father = f;
    }

    public void addChild(Person c){
	children.add(c);
    }
}
