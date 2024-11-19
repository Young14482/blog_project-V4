package com.example.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // 생성자 자동 생성
@Controller
public class BoardController {

    // 3. 클래스에 final을 붙이면 readonly 상태가 되면서 반드시 초기화가 되야하는 상태가 됨 >> 아래의 기본생성자 사태 방지 가능
    private final BoardService boardService;

    // 1. 생성자가 파라미터를 필요로 할 경우 IOC에서 해당 타입이 있는지 확인하고 알아서 의존성 주입
    // 1. Dependency Injection(의존성 주입)
//    public BoardController(BoardService boardService) {
//        System.out.println("의존BoardController");
//        this.boardService = boardService;
//    }

    // 2. 컴포넌트 스캔 시 기본생성자가 동시에 존재할 경우 기본생성자가 우선순위를 가지고 생성되고 필드값은 null이 된다
//    public BoardController() {
//        System.out.println("기본BoardController");
//        System.out.println(boardService); // null
//    }
    // view resolver가 resources 내의 templates에서 return값과 동일한 파일이름을 찾고 불러옴
    @GetMapping("/")
    public String list(Model model) { // DS(request 객체를 model이라는 객체로 랩핑해서 전달함) >> model = request
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기(); // 당장 필요없는 컬럼 정보까지 가져오는 중
        model.addAttribute("models", boardList);
        return "list";
    }

    /*
    @GetMapping("/{articleId}")
	public String qnaDetail(@PathVariable Integer articleId, Model model) {
		QNA byPk = service.findById(articleId);
		if (byPk.getSecure()) {
			return "notFound";
		}
		model.addAttribute("QNA", byPk);
		return "qnaDetail";
	}
    */
    @GetMapping("/board/{id}/update")
    public String updateForm(Model model, @PathVariable int id) {
        // UpdateFormDTO == DetailDTO 이지만 유지보수를 생각해 나누는게 좋음
        BoardResponse.UpdateFormDTO target = boardService.게시글수정화면보기(id); // 아래의 게시글상세보기()와 같은 동작이지만 나누는게 유지보수가 좋음
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

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO){
        System.out.println(saveDTO); // @Data는 내부에 toString을 재정의해서 구현해준다.
        boardService.게시글쓰기(saveDTO);
        return "redirect:/"; // 주소를 반환하기
    } 

    @GetMapping("/board/save-form")
    public String saveForm(){
        return "save-form";
    }

    /**
     * unique의 유무 차이로 주소작성법이 달라짐 >> 주소 설계 구칙
     * 쿼리스트링(where절): /board?title=바다 >> title은 unique하지 않음
     * 패스변수(where절): /board/1 >> id는 primary key >> unique 함
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        model.addAttribute("model", boardDetail);
        return "detail";
    }
}

