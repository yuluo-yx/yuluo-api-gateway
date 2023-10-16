package Indi.yuluo.gateway.assist;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import indi.yuluo.gateway.assist.common.Result;
import org.junit.Test;

public class ApiTest {
	@Test
	public void register(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", "10001");
        paramMap.put("gatewayId", "api-gateway-g4");
        paramMap.put("gatewayName", "电商配送网关");
        paramMap.put("gatewayAddress", "127.0.0.1");

        String resultStr = HttpUtil.post("http://localhost:8001/wg/admin/config/registerGateway", paramMap, 350);
        System.out.println(resultStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result.getCode());
	}
}


