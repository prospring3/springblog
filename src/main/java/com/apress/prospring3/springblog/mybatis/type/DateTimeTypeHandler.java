/**
 * Created on Oct 27, 2011
 */
package com.apress.prospring3.springblog.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;

/**
 * @author Clarence
 *
 */
@MappedTypes(value=DateTime.class)
public class DateTimeTypeHandler extends BaseTypeHandler<DateTime> {
	
	public void setNonNullParameter(PreparedStatement ps, int i,
			DateTime parameter, JdbcType jdbcType) throws SQLException {

		ps.setTimestamp(i, new java.sql.Timestamp((parameter).getMillis()));
		
	}	
	
	public DateTime getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
	    java.sql.Timestamp sqlTimestamp = rs.getTimestamp(columnName);
	    if (sqlTimestamp != null) {
	      return new DateTime(sqlTimestamp.getTime());
	    }
		return null;
	}	
	
	public DateTime getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
	    java.sql.Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
	    if (sqlTimestamp != null) {
	      return new DateTime(sqlTimestamp.getTime());
	    }
		return null;
	}

}
