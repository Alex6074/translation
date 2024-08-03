package com.example.translation.database;

import com.example.translation.model.TranslationRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationRecordRepository {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createRecordSQL = "INSERT INTO translation_record (ip, input_text, translated_text) VALUES (?,?,?);";

    public void save(TranslationRecord record) {
        Long recordId = null;
        try {
            connection = TranslationDataSource.getInstance().getConnection();
            if (connection != null) {
                ps = connection.prepareStatement(createRecordSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, record.getIp());
                ps.setString(2, record.getInputText());
                ps.setString(3, record.getTranslatedText());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    recordId = rs.getLong(1);
                }
                ps.close();
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
