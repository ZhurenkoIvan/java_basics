import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

public class Main {
    public static void main(String[] args) {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure("hibernate.cfg.xml").build();
//        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
//        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//
//        Session session = sessionFactory.openSession();
//
//        Course course = session.get(Course.class, 1);
//        System.out.println(course.getName());
//        System.out.println("Количество студентов: " + course.getStudentsCount());
//
//        sessionFactory.close();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernateStudents.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Student student = session.get(Student.class, 2);

        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getRegistrationDate());

        sessionFactory.close();

    }
}
