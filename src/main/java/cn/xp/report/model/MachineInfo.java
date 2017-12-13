package cn.xp.report.model;

import lombok.Data;

/**
 * 机器信息封装
 * 
 */
@Data
public class MachineInfo {

	private String machine_id;

	private String machine_name;

    private int capacity ;

    private String type = "";

    private String describe = "";
}
