package bearkid.com.bearkiddiaryfamily.model.bean;

/**
 * 服务器返回的数据格式
 * @author admin
 * 
 * @param <T>
 */
public class Result<T> {
	/**
	 * 0:表示成功
	 * 1：表示失败
	 */
	private int resultCode;
	private String resultMessage;
	private T data;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "resultCode = " + resultCode
				+ "resultMessage = " + resultMessage
				+ "data = " + data.toString();
	}
}
