package com.my.core.commandManager.data;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 任务信息持久层实现
 *
 * @author M.Y
 */

public class TaskDaoImpl implements TaskDao {
	/**
	 * 存放任务信息
 	 */
	private final ConcurrentMap<String, CommandTasker> map;

	public TaskDaoImpl(int size) {
		map = new ConcurrentHashMap<>(size);
	}

	@Override
	public CommandTasker get(String id) {
		return map.get(id);
	}

	@Override
	public Collection<CommandTasker> getAll() {
		return map.values();
	}

	@Override
	public int add(CommandTasker commandTasker) {
		String id = commandTasker.getId();
		if (id != null && !map.containsKey(id)) {
			map.put(commandTasker.getId(), commandTasker);
			if(map.get(id)!=null)
			{
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int remove(String id) {
		if(map.remove(id) != null){
			return 1;
		}
		return 0;
	}

	@Override
	public int removeAll() {
		int size = map.size();
		try {
			map.clear();
		} catch (Exception e) {
			return 0;
		}
		return size;
	}

	@Override
	public boolean isHave(String id) {
		return map.containsKey(id);
	}

}
