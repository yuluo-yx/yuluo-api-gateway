package indi.yuluo.gateway.session;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import io.netty.channel.Channel;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public interface IGenericReferenceSessionFactory {

	Future<Channel> openSession() throws ExecutionException, InterruptedException;

}
