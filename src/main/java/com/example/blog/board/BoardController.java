package com.example.blog.board;

import com.example.blog._core.util.Resp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api")
    public Resp<?> list() {
        return Resp.ok(boardService.게시글목록보기());
    }

    @PostMapping("/api/board")
    public Resp<?> save(@Valid @RequestBody BoardRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return Resp.ok(null);
    }

    @PutMapping("/api/board/{id}")
    public Resp<?> update(@PathVariable int id, @Valid @RequestBody BoardRequest.UpdateDTO updateDTO) {
        boardService.게시글수정하기(id,updateDTO);
        return Resp.ok(null);
    }

    @DeleteMapping("/api/board/{id}")
    public Resp<?> delete(@PathVariable int id) {
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }

    @GetMapping("/api/board/{id}")
    public Resp<?> detail(@PathVariable("id") Integer id) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        return Resp.ok(boardDetail);
    }
}

