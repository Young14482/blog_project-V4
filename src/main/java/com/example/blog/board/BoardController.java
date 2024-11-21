package com.example.blog.board;

import com.example.blog._core.error.ex.Exception400;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String list(Model model) {
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        model.addAttribute("models", boardList);
        return "list";
    }

    @GetMapping("/board/{id}/update")
    public String updateForm(Model model, @PathVariable Integer id) {
        // UpdateFormDTO == DetailDTO 이지만 유지보수를 생각해 나누는게 좋음
        BoardResponse.UpdateFormDTO target = boardService.게시글수정화면보기(id);
        model.addAttribute("model", target);
        return "update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO updateDTO) {
        boardService.게시글수정하기(id,updateDTO);
        return "redirect:/board/"+ id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id) {
        boardService.게시글삭제(id);
        return"redirect:/";
    }

    @PostMapping("/board/save") // @Valid >> 바로 옆에 있는 변수에 값을 넣어 줌 주의해야함
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {

        boardService.게시글쓰기(saveDTO);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm(){
        return "save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        model.addAttribute("model", boardDetail);
        return "detail";
    }
}

