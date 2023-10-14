package indi.yuluo.gateway.center.infrastructure.dao;

import java.util.List;

import indi.yuluo.gateway.center.infrastructure.po.HttpStatement;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@Mapper
public interface IHttpStatementDao {

	List<HttpStatement> queryHttpStatementList();

}
