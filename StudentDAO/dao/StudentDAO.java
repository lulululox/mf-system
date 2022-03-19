package dao;
import bean.*;

public interface StudentDAO{
    public void insert(Student s);
    public void update(Student s);
    public void delete(String iD);
    public void findByID(long iD);
    public void findAll();
} 

