package adri.suivi_courrier.data.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class CourrierIdGenerator {
    private DataSource dataSource;

    public CourrierIdGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String generateId() throws SQLException {
        LocalDate currentDate = LocalDate.now();
        int anneeActuelle = currentDate.getYear();

        int maxSequence = getMaxSequenceForCurrentYear(anneeActuelle);
        int newSequence = (maxSequence > 0) ? maxSequence + 1 : 1;

        return String.format("%d-%05d", anneeActuelle, newSequence);
    }

    private int getMaxSequenceForCurrentYear(int year) throws SQLException {
        String sql = "SELECT COALESCE(MAX(CAST(SUBSTRING(id_courrier FROM 6 FOR 5) AS INT)), 0) " +
                     "FROM Courrier WHERE id_courrier LIKE ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, year + "-%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }


}
