package DAO;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDAOTest {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

// 1.     Persisting into database
        User newUser = new User();
        newUser.setEmail("tolu@gmail.com");
        newUser.setFullname("tolu tom");
        newUser.setPassword("tt");
        entityManager.persist(newUser);

        User newUser1 = new User();
        newUser1.setEmail("abc@gmail.com");
        newUser1.setFullname("ab abb");
        newUser1.setPassword("ababb");
        entityManager.persist(newUser1);

        User newUser2 = new User();
        newUser2.setEmail("billgates@gmail.com");
        newUser2.setFullname("bill gates");
        newUser2.setPassword("billgates");
        entityManager.persist(newUser2);

        User newUser3 = new User();
        newUser3.setEmail("dami@gmail.com");
        newUser3.setFullname("dami Joy");
        newUser3.setPassword("damijoy");
        entityManager.persist(newUser3);

 //2.         updating into the database
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail("bill.joy@gmail.com");
        existingUser.setFullname("Bill Joy");
        existingUser.setPassword("billionaire");

        entityManager.merge(existingUser);

  //3.   reading the database/ printoing from the db
        Integer primaryKey = 1;
        User user = entityManager.find(User.class, primaryKey);

        System.out.println(user.getEmail());
        System.out.println(user.getFullname());
        System.out.println(user.getPassword());

 //4.        JPQL queries
        String sql = "SELECT u from User u where u.email = 'tolu@gmail.com'";
        Query query = entityManager.createQuery(sql);
        User user2 = (User) query.getSingleResult();

        System.out.println(user2.getEmail());
        System.out.println(user2.getFullname());
        System.out.println(user2.getPassword());


 //5.       DELETING FROM THE DATABASE

        Integer primaryKey1 = 5;
        User reference = entityManager.getReference(User.class, primaryKey1);
        entityManager.remove(reference);



        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
