package ua.com.alevel;

import ua.com.alevel.entity.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.time.Instant;

public class NextLessonByStudentId {
    public void findNextLesson(EntityManager entityManager, Long studentId) {
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Lesson> query = entityManager.createQuery("""
                    SELECT l FROM Lesson l JOIN
                    l.group g JOIN
                    g.students s WHERE s.id = ?1 AND l.timestamp > ?2
                    ORDER BY l.timestamp""", Lesson.class);
            query.setParameter(1, studentId);
            query.setParameter(2, Timestamp.from(Instant.now()));
            Lesson lesson = query.getSingleResult();
            System.out.println(lesson.toString());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
