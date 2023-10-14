package indi.yuluo.gateway.socket.agreement;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alibaba.fastjson2.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 * HTTP 请求参数解析
 */

public class RequestParser {

	private final FullHttpRequest request;

	public RequestParser(FullHttpRequest request) {
		this.request = request;
	}

	public Map<String, Object> parser() {

		// 获取 Content-type
		String contentType = getContentType();
		// 获取请求类型
		HttpMethod method = request.method();

		if (HttpMethod.GET == method) {

			Map<String, Object> parameterMap = new HashMap<>();
			QueryStringDecoder decoder = new QueryStringDecoder(request.uri());

			decoder.parameters().forEach((key, value) -> {
				parameterMap.put(key, value.get(0));
			});
			return parameterMap;
		} else if (HttpMethod.POST == method) {
			// 针对 post 请求，暂时只分为两类进行处理
			switch (contentType) {
			// form 表单
			case "multipart/form-data":
				HashMap<String, Object> parameterMap = new HashMap<>();
				HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
				decoder.offer(request);
				decoder.getBodyHttpDatas().forEach(data -> {
					Attribute attr = (Attribute) data;
					try {
						parameterMap.put(data.getName(), attr.getValue());
					} catch (IOException ignore) {
					}
				});

				return parameterMap;
			// json
			case "application/json":
				ByteBuf byteBuf = request.content().copy();
				if (byteBuf.isReadable()) {

					String content = byteBuf.toString(StandardCharsets.UTF_8);
					return JSON.parseObject(content);
				}
				break;
			default:
				throw new RuntimeException("未实现的协议类型 content-Type: " + contentType);
			}
		}
		throw new RuntimeException("未实现的请求类型 Http-Method: " + method);
	}

	private String getContentType() {

		Optional<Map.Entry<String, String>> header = request
				.headers()
				.entries()
				.stream()
				.filter(
					val -> val
							.getKey()
							.equals("Content-Type")
				).findAny();

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Get Content-Type 解析参数 Header：" + header);
		System.out.println("---------------------------------------------------------------------------");


		Map.Entry<String, String> entry = header.orElse(null);
		assert entry != null;
		// application/json、multipart/form-data;
		String contentType = entry.getValue();
		int idx = contentType.indexOf(";");
		if (idx > 0) {
			return contentType.substring(0, idx);
		} else {
			return contentType;
		}

	}

	/**
	 * /sayHi?str=yuluo  --> /sayHi
	 * /sayHi --> /sayHi
	 * @return uri
	 */
	public String getUri() {

		String uri = request.uri();

		int idx = uri.indexOf("?");
		uri = idx > 0 ? uri.substring(0, idx) : uri;
		if (uri.equals("/favicon.ico")) {
			return null;
		}

		return uri;
	}

	public static void main(String[] args) {
		String uri = "https://127.0.0.1:18080/test?id=1";
		int idx = uri.indexOf("?");
		uri = idx > 0 ? uri.substring(0, idx) : uri;


		System.out.println(uri);
	}
}
