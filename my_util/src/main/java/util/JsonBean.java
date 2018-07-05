package util;

import java.util.Map;

public class JsonBean {
        public String code;  
        public Map<String,String> data;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Map<String, String> getData() {
			return data;
		}
		public void setData(Map<String, String> data) {
			this.data = data;
		}
}  
