package com.w.push.entity;


public class Type {

	public enum MsgType {
		GAME_INNER(0,"游戏内"),
		GAME_OUTER(1,"游戏外");
		private int id;
		private String name;
		private MsgType(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public static String valueOf(int id) {
			for(MsgType mt : values()) {
				if(mt.id == id) {
					return mt.name;
				}
			}
			return null;
		}
	}
	
	public enum Cmd {
		CMD_TXT(1,"纯文本"),
		CMD_WEBPAGE(2,"链接"),
		CMD_DOWNLOAD(3,"下载"),
		CMD_OPEN_GAME(4,"打开游戏");
		private int id;
		private String name;
		private Cmd(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public static String valueOf(int id) {
			for(Cmd mt : values()) {
				if(mt.id == id) {
					return mt.name;
				}
			}
			return null;
		}
	}
	
	public enum Status {
		INIT(0,"未发布"),
		PUB(1,"发布"),
		DEL(-1,"已删除");
		private int id;
		private String name;
		private Status(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public static String valueOf(int id) {
			for(Status mt : values()) {
				if(mt.id == id) {
					return mt.name;
				}
			}
			return null;
		}
	}
	
}
