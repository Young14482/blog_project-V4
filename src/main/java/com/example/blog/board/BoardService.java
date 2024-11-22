package com.example.blog.board;

import com.example.blog._core.error.ex.Exception404;
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
                .orElseThrow(() -> new Exception404("해당 id의 게시글이 없습니다: " + id));
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
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id의 게시글이 없습니다: " + id));
        board.update(updateDTO.getTitle(), updateDTO.getContent());
    }

}
