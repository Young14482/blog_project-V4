package com.example.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest // DB에 관련된 자원들을 메모리(IOC)에 올림
public class BoardRepositoryTest {
    
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void update_test() {
        // given
        int id = 1;
        String title = "바뀐 제목123";
        String content = "바뀐 내용123123";

        // when
        boardRepository.update(id, title, content);

        //then(eye)
        Board board = boardRepository.findById(id);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void delete_test(){
        //given
        int id = 1;

        //when
        boardRepository.delete(id);

        //then(eye)
        List<Board> boardList = boardRepository.findAll();
        System.out.println("사이즈? " + boardList.size());
    }

    @Test
    public void save_test() {
        // given
        String title = "제목6";
        String content = "내용6";

        // when
        boardRepository.save(title, content);

        // then(eye)
        Board board = boardRepository.findById(6);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    } // rollback >> @Transactional 효과 >> 메소드가 끝나면 rollback

    @Test
    public void findAll_test(){
        // test 3단계
        // given >> 테스트할 메소드의 파라미터를 넣음 >> 없으면 생략
        
        // when >> 테스트할 메소드 작성
        List<Board> boardList = boardRepository.findAll();
        System.out.println(); // 디버그 찍는 용도의 출력메소드 (eye)

        // then >> 배포 전 상태 검증 인데 >> 초보는 eye >> 눈으로 출력물 직접 확인
        for(Board board : boardList){
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("============");
        }
    }
}
