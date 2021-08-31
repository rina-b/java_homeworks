package ua.com.alevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JPAMain {
    public static void main(String[] args) {
        long studentId = Long.parseLong(args[0]);

        Configuration conf = new Configuration().configure();
        try (SessionFactory sessionFactory = conf.buildSessionFactory()) {
            Session entityManager = (Session) sessionFactory.createEntityManager();
            NextLessonByStudentId nextLessonByStudentId = new NextLessonByStudentId();
            nextLessonByStudentId.findNextLesson(entityManager, studentId);
        }
    }
}
