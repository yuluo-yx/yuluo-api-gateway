package indi.yuluo.gateway.util;

import java.text.SimpleDateFormat;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

public final class DateTimeFormat {

	private DateTimeFormat() {}

	public static String format(Long time) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return simpleDateFormat.format(Long.parseLong(String.valueOf(time)));
	}

}
