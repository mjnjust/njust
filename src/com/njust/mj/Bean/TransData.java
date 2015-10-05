package com.njust.mj.Bean;

//服务器与客户端之间交换数据的类
public class TransData {
	private String srcId = null ;
	private String desId = null ;
	private byte[] data = null ;
	private int type = -1;//0 字符串  1文件 2图片 3登陆 4下线
	public TransData() {
		
	}
	public TransData(byte[] fromclient) {
		System.out.println("fromclient:"+fromclient.length);
		int index = 0;
		this.type = (int)fromclient[index++];
		
		int srcIdlength = -1;
		int desIdlength = -1;
		if(this.type!=3&&this.type!=4){
			desIdlength = (int) fromclient[index++];
			this.desId = new String(fromclient, index, desIdlength);
			index = index+desIdlength;
		}else{
			srcIdlength = (int)fromclient[index++];
			this.srcId = new String(fromclient, index, srcIdlength);
			index = index+srcIdlength;
		}
		this.data = new byte[fromclient.length-index];
		for(int i=0;i<data.length;i++){
			this.data[i] = fromclient[index++];
		}
	}
	public byte[] tobyte() {
		byte[] srcIdbyte = null;
		
		byte[] desIdbyte = null;
		int a = 0;
		int b = 0;
		if(this.srcId!=null&&this.srcId.length()>0){
			srcIdbyte = this.srcId.getBytes();
			a = srcIdbyte.length+1;
		}
		if(this.desId!=null&&this.desId.length()>0){
			desIdbyte = this.desId.getBytes();
			b = desIdbyte.length+1;
		}
		byte[] ans = new byte[1+a+b+this.data.length];
		int index = 0;
		ans[index++] = (byte) this.type;
		if(a!=0){
			ans[index++] = (byte) (a-1) ;
			for(int i=0;i<a-1;i++){
				ans[index++] = srcIdbyte[i];
			}
		}
		if(b!=0){
			ans[index++] = (byte) (b-1) ;
			for(int i=0;i<b-1;i++){
				ans[index++] = desIdbyte[i];
			}
		}
		for(int i=0;i<this.data.length;i++){
			ans[index++] = this.data[i];
		}
		return ans;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getdesId() {
		return desId;
	}
	public void setdesId(String Id) {
		this.desId = Id;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	
}
