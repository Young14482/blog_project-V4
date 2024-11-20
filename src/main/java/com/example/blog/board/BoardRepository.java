package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;


    public List<Board> findAll() {
        // 테이블 이름이 아니라 클래스명(Board)과 변수(b)를 사용
        return em.createQuery("SELECT b FROM Board b ORDER BY b.id DESC", Board.class).getResultList();
    }

    public Optional<Board> findById(int id) {
        return Optional.ofNullable(em.find(Board.class, id));
    }

    public void save(Board board) {
        // 실행 전 board 비영속
       em.persist(board); // 객체를 만들어서 던지면 insert
        // 실행 후 board 영속 >> 비어있던 필드들이 채워진 상태
    }

    public void delete(int id) {
        // createQuery에서 ? 대신 ":파라미터명" 으로 지정후 setParameter로 값을 넣어 줄 수 있다.
       em.createQuery("DELETE FROM Board b WHERE b.id = :id")
               .setParameter("id", id)
               .executeUpdate();
    }
    // update는 이제 안써도됨 >> 상태 변경을 감지함
}
