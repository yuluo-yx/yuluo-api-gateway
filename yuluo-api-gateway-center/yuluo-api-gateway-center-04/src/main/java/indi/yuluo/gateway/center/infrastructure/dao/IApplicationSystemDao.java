package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IApplicationSystemDao {

	void insert(ApplicationSystem applicationSystem);

	List<ApplicationSystem> queryApplicationSystemList(List<String> list);

}
