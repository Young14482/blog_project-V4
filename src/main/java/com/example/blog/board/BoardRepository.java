package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    // JPA는 EntityManager를 통해 DB에 접근한다. (자바에서 DBConnection)
    private final EntityManager em;

    // db를 조회하고 내용을 알아서 매핑해줌
    public List<Board> findAll() {
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return q.getResultList();
    }

    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        q.setParameter(1, id); // 물음표 완성하기 >> (물음표 순서, 물음표에 바인딩할 변수값)
        return (Board) q.getSingleResult();
    }

    public void save(String title, String content) {
        Query q = em.createNativeQuery("insert into board_tb(title, content, created_at) values(?, ?, now())", Board.class);
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.executeUpdate(); // insert, update, delete는 실행시켜줘야한다.
    }

    public void delete(int id) {
        Query q = em.createNativeQuery("delete from board_tb where id = ?");
        q.setParameter(1,id);
        q.executeUpdate();
    }
}
