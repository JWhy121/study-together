package com.elice.studytogether.repository;


import com.elice.studytogether.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class jdbcTemplateBoardReposiroty{

    private final JdbcTemplate jdbcTemplate;

    public jdbcTemplateBoardReposiroty(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //게시판 생성
    public Board save(Board board) {
        String sql = "insert into board(board_title, description) values(?, ?)";
        int postId = jdbcTemplate.update(sql, board.getBoard_title(), board.getDescription());
        board.setId((long) postId);
        return board;
    }

    //게시판 수정
    public int update(Board board) {
        String sql = "update board set board_title = ? where id = ?";
        int result = jdbcTemplate.update(sql, board.getBoard_title(), board.getId());
        return result;
    }

    //게시판 전체 조회
    public List<Board> findAll() {
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    //게시판 id를 조건으로 조회
    public Optional<Board> findById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        return jdbcTemplate.query(sql, boardRowMapper(), id).stream().findAny();
    }

    //게시판 삭제
    public void deleteById(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setBoard_title(rs.getString("board_title"));
            board.setDescription(rs.getString("description"));
            return board;
        };
    }


}
