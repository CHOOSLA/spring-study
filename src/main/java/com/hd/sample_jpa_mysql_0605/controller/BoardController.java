package com.hd.sample_jpa_mysql_0605.controller;

import com.hd.sample_jpa_mysql_0605.dto.BoardModifyDto;
import com.hd.sample_jpa_mysql_0605.dto.BoardResDto;
import com.hd.sample_jpa_mysql_0605.dto.BoardWriteDto;
import com.hd.sample_jpa_mysql_0605.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:5173"
})
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/all")
    public ResponseEntity<List<BoardResDto>> all(){
        return ResponseEntity.ok(boardService.findAll());
    }

    @PostMapping("/write")
    public ResponseEntity<Boolean> write(@RequestBody BoardWriteDto boardWriteDto){
        return ResponseEntity.ok(boardService.writeBoard(boardWriteDto));
    }

    @GetMapping("/detail/{boardId}")
    public ResponseEntity<BoardResDto> detail(@PathVariable Long boardId){
        return ResponseEntity.ok(boardService.getBoardDetail(boardId));
    }

    @PutMapping("/modify")
    public ResponseEntity<Boolean> modify(@RequestBody BoardModifyDto boardModifyDto){
        return ResponseEntity.ok(boardService.modifyBoard(boardModifyDto));
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long boardId){
        return ResponseEntity.ok(boardService.deleteBoard(boardId));
    }

    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<BoardResDto>> findByTitle(@PathVariable String title){
        return ResponseEntity.ok(boardService.searchBoardByTitle(title));
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<List<BoardResDto>> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(boardService.searchBoardByEmail(email));
    }
}
