package gukbi.bookplybackend.common.dto;

import java.util.HashMap;
import java.util.Map;

public class ResponseDTO extends HashMap<String, Map<String, Object>> {

  public ResponseDTO() {
    super.put("common", new HashMap<>());
    super.put("data", new HashMap<>());
  }

  public void setCommon(String key, Object value) {
    super.get("common").put(key, value);
  }

  public void setResCode(Object value) {
    super.get("common").put("res_code", value);
  }

  public void setResMsg(Object value) {
    super.get("common").put("res_msg", value);
  }

  public void setData(String key, Object value) {
    super.get("data").put(key, value);
  }

  public Object getCommon(String key) {
    return super.get("common").getOrDefault(key, null);
  }

  public Object getData(String key) {
    return super.get("data").getOrDefault(key, null);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    return sb.append("common : ").append(super.get("common"))
        .append(", data : ").append(super.get("data")).toString();
  }
}