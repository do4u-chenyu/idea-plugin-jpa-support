package com.ifengxue.plugin.util;

import com.ifengxue.plugin.entity.Column;
import java.sql.Timestamp;
import java.util.Date;

public class ColumnUtil {

  public static void parseColumn(Column column, String removePrefix, boolean isUseWrapper) {
    column.setFieldName(StringHelper.parseFieldName(column.getColumnName(), removePrefix));
    Class<?> javaDataType = StringHelper
        .parseJavaDataType(column.getDbDataType(), column.getColumnName(), isUseWrapper);
    column.setJavaDataType(javaDataType);
    if (column.getDefaultValue() != null) {
      if (javaDataType == String.class) {
        column.setDefaultValue("\"" + column.getDefaultValue() + "\"");
      }
      Class<?> primitiveClass = StringHelper.getPrimitiveClass(javaDataType);
      if (primitiveClass == long.class) {
        column.setDefaultValue(column.getDefaultValue() + "L");
      }
      if (primitiveClass == float.class) {
        column.setDefaultValue(column.getDefaultValue() + "F");
      }
      if (primitiveClass == double.class) {
        column.setDefaultValue(column.getDefaultValue() + "D");
      }
      if (primitiveClass == Date.class) {
        column.setDefaultValue("new Date()");
      }
      if (primitiveClass == Timestamp.class) {
        column.setDefaultValue("new Timestamp(System.currentTimeMillis())");
      }
      column.setHasDefaultValue(true);
    }
  }
}
