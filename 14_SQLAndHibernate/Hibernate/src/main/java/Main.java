import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build())
              {
                  Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()){
                Session session = sessionFactory.openSession();

                Transaction transaction = session.beginTransaction();

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<PurchaseList> purchaseListQuery = builder.createQuery(PurchaseList.class);
                Root<PurchaseList> purchaseListRoot = purchaseListQuery.from(PurchaseList.class);
                purchaseListQuery.select(purchaseListRoot);
                List<PurchaseList> purchaseList = session.createQuery(purchaseListQuery).getResultList();

                CriteriaQuery<Student> studentQuery = builder.createQuery(Student.class);
                Root<Student> studentRoot = studentQuery.from(Student.class);

                CriteriaQuery<Course> courseQuery = builder.createQuery(Course.class);
                Root<Course> courseRoot = courseQuery.from(Course.class);

                for (PurchaseList purchase : purchaseList) {
                    studentQuery.select(studentRoot).where(builder.equal(studentRoot.get("name"), purchase.getId().getStudentName()));
                    courseQuery.select(courseRoot).where(builder.equal(courseRoot.get("name"), purchase.getId().getCourseName()));

                    List<Student> studentList = session.createQuery(studentQuery).getResultList();
                    List<Course> courseList = session.createQuery(courseQuery).getResultList();

                    LinkedPurchaiseList.Key key = new LinkedPurchaiseList.Key();
                    key.setStudentId(studentList.get(0).getId());
                    key.setCourseId(courseList.get(0).getId());
                    LinkedPurchaiseList linkedPurchaiseList = new LinkedPurchaiseList();
                    linkedPurchaiseList.setId(key);

                    session.save(linkedPurchaiseList);
                }
                transaction.commit();
            }
        }
    }
}
