package org.flab.api.domain.event.repository.seat;

import lombok.RequiredArgsConstructor;
import org.flab.api.domain.event.domain.seat.Seat;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BulkInsertRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<Seat> seatList) {

        String sql = "INSERT INTO SEAT(show_id, zone_id, row_number, col_number, status, created_at, updated_at) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, seatList.get(i).getShow().getId());
                        ps.setLong(2, seatList.get(i).getZone().getId());
                        ps.setLong(3, seatList.get(i).getRowNumber());
                        ps.setLong(4, seatList.get(i).getColNumber());
                        ps.setString(5, seatList.get(i).getStatus().toString());
                        ps.setString(6, seatList.get(i).getCreatedAt().toString());
                        if(seatList.get(i).getUpdatedAt() != null) {
                            ps.setString(7, seatList.get(i).getUpdatedAt().toString());
                        } else {
                            ps.setNull(7, java.sql.Types.TIMESTAMP);
                        }
                    }

                    @Override
                    public int getBatchSize() {
                        return seatList.size();
                    }
                });
    }
}
