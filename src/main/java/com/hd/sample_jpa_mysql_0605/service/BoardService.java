package com.hd.sample_jpa_mysql_0605.service;

import com.hd.sample_jpa_mysql_0605.dto.BoardModifyDto;
import com.hd.sample_jpa_mysql_0605.dto.BoardResDto;
import com.hd.sample_jpa_mysql_0605.dto.BoardWriteDto;
import com.hd.sample_jpa_mysql_0605.entity.Board;
import com.hd.sample_jpa_mysql_0605.entity.Member;
import com.hd.sample_jpa_mysql_0605.repository.BoardRepository;
import com.hd.sample_jpa_mysql_0605.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 등록 : Boolean
    public boolean writeBoard(BoardWriteDto boardWriteDto){
        try{
            Board board = convertDtoToEntity(boardWriteDto);
            if(board==null) throw new Exception();
        boardRepository.save(board);
        return true;
        }catch (Exception e){
            log.error("글 쓰기 작업 실패");
            return false;
        }
    }

    // 게시글 전체 조회 : List<Board>
    public List<BoardResDto> findAll(){
        List<Board> boards = boardRepository.findAll();
        List<BoardResDto> result = new ArrayList<>();
        for(Board board : boards){
            result.add( convertEntityToDto(board));
        }

        return result;
    }

    // 게시글 상세 조회 : Board
    public BoardResDto getBoardDetail(Long boardId){
        try{
            Board board = boardRepository.findById(boardId).orElseThrow(
                    () -> new RuntimeException("글이 사졌습니다")
            );

            return convertEntityToDto(board);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    // 게시글 수정 : Boolean
    public Boolean modifyBoard(BoardModifyDto boardModifyDto){
        try{
            Board board = boardRepository.findById(boardModifyDto.getBoardId()).orElseThrow(
                    () -> new RuntimeException("글을 수정할 수 없습니다")
            );

            board.setTitle(boardModifyDto.getTitle());
            board.setContent(boardModifyDto.getContent());
            board.setImage(boardModifyDto.getImg());
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    // 게시글 삭제 : Boolean
    public Boolean deleteBoard(Long boardId){
        try{
            Board board = boardRepository.findById(boardId).orElseThrow(
                    () -> new RuntimeException("삭제할 수 없다")
            );
            boardRepository.delete(board);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    // 게시글 검색 : List<Board>
    public List<BoardResDto> searchBoardByTitle(String title){
        List<Board> boards = boardRepository.findByTitleContaining(title);
        List<BoardResDto> result = new ArrayList<>();
        for(Board board : boards){
            result.add( convertEntityToDto(board));
        }

        return result;
    }

    public List<BoardResDto> searchBoardByEmail(String email){
        List<Board> boards = boardRepository.findByMemberEmail(email);
        List<BoardResDto> result = new ArrayList<>();
        for(Board board : boards){
            result.add( convertEntityToDto(board));
        }

        return result;
    }

    // 페이지네이션

    // 엔티티를 DTO를 만드는 과정
    private BoardResDto convertEntityToDto(Board board){
        BoardResDto boardResDto = new BoardResDto();
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setBoardId(board.getId());
        boardResDto.setCreateTime(board.getCreateTime());
        boardResDto.setImg(board.getImage());
        boardResDto.setEmail(board.getMember().getEmail());

        return boardResDto;
    }
    // DTO를 엔티티로 만드는 과정
    private Board convertDtoToEntity(BoardWriteDto boardWriteDto){
        Board board = new Board();
        try{
            Member member = memberRepository.findByEmail(boardWriteDto.getEmail()).orElseThrow(
                    ()-> new RuntimeException("올바르지 않은 접근")
            );

            board.setMember(member);
        }catch (Exception e){
            log.error("올바르지 않은 접근");
            return null;
        }

        board.setTitle(boardWriteDto.getTitle());
        board.setContent(boardWriteDto.getContent());
        board.setImage(boardWriteDto.getImg());

        return board;
    }

}
