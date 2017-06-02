package com.dily.mappers;

import com.dily.entities.Documents;
import com.dily.entities.Media;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rusum on 02.06.2017.
 */
public final class DocumentsMapper implements RowMapper<Documents> {
    public Documents mapRow(ResultSet rs, int rowNum) throws SQLException {
        Documents documents = new Documents();
        documents.setDocumentId(rs.getInt("document_id"));
        documents.setUserId(rs.getInt("user_id"));
        documents.setPath(rs.getString("document_path"));
        return documents;
    }
}