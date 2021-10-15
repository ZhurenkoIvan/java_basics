import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

public class Main {
    public static void main(String[] args) {


        try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build())
              {
                  Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()){
                Session session = sessionFactory.openSession();

                Student student = session.get(Student.class, 2);
                PurchaseList purchaseList = session.get(PurchaseList.class, new PurchaseList.PurchaseKey("Амбражевич Порфирий", "Веб-разработчик c 0 до PRO"));
                System.out.println(purchaseList.getId().getCourseName());
                System.out.println(purchaseList.getId().getStudentName());
                System.out.println(purchaseList.getSubscriptionDate());
                System.out.println(purchaseList.getPrice());

                System.out.println(student.getId());
                System.out.println(student.getName());
                System.out.println(student.getAge());
                System.out.println(student.getRegistrationDate());
            }
        }
    }
}
