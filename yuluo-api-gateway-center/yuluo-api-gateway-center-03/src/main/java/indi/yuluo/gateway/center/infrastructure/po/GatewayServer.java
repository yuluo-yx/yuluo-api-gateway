package indi.yuluo.gateway.center.infrastructure.po;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */
public class GatewayServer {

	/** 自增主键 */
	private Integer id;

	/** 分组标识 */
	private String groupId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/** 分组名称 */
	private String groupName;


}
