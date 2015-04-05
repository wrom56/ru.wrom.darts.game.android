package ru.wrom.darts.game.core.api;


public class GameType {

	private GameTypeCode code;
	private String param;

	public GameTypeCode getCode() {
		return code;
	}

	public void setCode(GameTypeCode code) {
		this.code = code;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
