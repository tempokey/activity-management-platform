package com.edudigital.cloudy.amp.file.base.constant.enumeration;


public enum FileStatus {	
		// 成功
		SUCCESS(1000, "success"),
		// 不存在
		UNDEFINED(4000, "undefined"),
		// 失败
		ERROR(5000, "error");
		// 成员变量
		private int index;
		private String type;

		// 构造方法
		private FileStatus(int index, String type) {
			this.index = index;
			this.type = type;
		}

		// 普通方法
		public static String getType(int index) {
			for (FileStatus o : FileStatus.values()) {
				if (o.getIndex() == index) {
					return o.type;
				}
			}
			return null;
		}

		public String toString() {
			return type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	
	

}
