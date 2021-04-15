package com.my.core.commandManager.config;

import lombok.Data;

/**
 * 程序基础配置
 * 
 * @author eguid
 * 
 */
@Data
public class ProgramConfig {
	
	// 默认命令行执行根路径
	private String path;
	//是否开启debug模式
	private boolean debug;
	//任务池大小
	private Integer size;
	//回调通知地址
	private String callback;
	//是否开启保活
	private boolean keepalive;

	@Override
	public String toString() {
		return "ProgramConfig [path=" + path + ", debug=" + debug + ", size=" + size + ", callback=" + callback
				+ ", keepalive=" + keepalive + "]";
	}
}
