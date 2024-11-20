package com.example.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse.DTO> 게시글목록보기() {

        return boardRepository.findAll()
                .stream().map(BoardResponse.DTO::new)
                .toList();
    }

    public BoardResponse.DetailDTO 게시글상세보기(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다: " + id));
        return new BoardResponse.DetailDTO(board);
    }

    @Transactional // 커밋용
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
       boardRepository.save(saveDTO.toEntity());
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    }

    @Transactional

    public void 게시글수정하기(int id, BoardRequest.UpdateDTO updateDTO) {
        // 수정할 board 찾기
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다: " + id));
        // 찾은 board 내용 바꾸기 >> 영속화된 객체 상태 변경 >> update + commit => dirty checking
        board.update(updateDTO.getTitle(), updateDTO.getContent());
    }

    public BoardResponse.UpdateFormDTO 게시글수정화면보기(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다: " + id));
        return new BoardResponse.UpdateFormDTO(board);
    }
}
