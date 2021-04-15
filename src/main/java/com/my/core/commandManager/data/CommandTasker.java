package com.my.core.commandManager.data;

import com.my.core.commandManager.handler.OutHandler;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用于存放任务id,任务主进程，任务输出线程
 */
@Data
public class CommandTasker {
	// 任务id
	private final String id;
	//命令行
	private final String command;
	// 命令行运行主进程
	private Process process;
	// 命令行消息输出子线程
	private OutHandler thread;

	private LocalDateTime createTime;

	public CommandTasker(String id,String command) {
		this.id = id;
		this.command=command;
		this.createTime = LocalDateTime.now();
	}

	public CommandTasker(String id,String command, Process process, OutHandler thread) {
		this.id = id;
		this.command=command;
		this.process = process;
		this.thread = thread;
		this.createTime = LocalDateTime.now();
	}

}
