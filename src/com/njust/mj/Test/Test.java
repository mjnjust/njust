package com.njust.mj.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;






import com.njust.mj.Bean.TransData;
import com.njust.mj.Controll.ServiceMain;

public class Test {
	@org.junit.Test
	public void testTransData() {
		String ida = "clientaÃ«¿¡" ;
		byte[] x = ida.getBytes();
		byte[] data = new byte[x.length+2];
		int index = 0;
		data[index++] = 0;
		data[index++] =(byte) x.length;
		for(int i=0;i<x.length;i++){
			data[index++] = x[i];
		}
		TransData taData = new TransData(data);
		System.out.println(taData.getdesId());
	}
	public static void main(String[] args) throws Exception{
		Socket socket = null ;
		socket = new Socket("127.0.0.1", 8819);
		if(socket!=null){
			Scanner scanner =new Scanner(System.in);
			Writer writer = new OutputStreamWriter(socket.getOutputStream());
			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader bufferedReader = new  BufferedReader(reader);
			new Thread(new Runnable() {
				@Override
				public void run() {
					int a = 0;
					String string = null ;
					while((string = scanner.nextLine())!=null){
						try {
							TransData transData = new TransData();
							if(a == 0){
								transData.setSrcId("maojuna");
								transData.setType(string.charAt(0)-'0');
								transData.setData(new byte[0]);
								a++;
							}else{
								transData.setdesId("maojunb");
								transData.setType(string.charAt(0)-'0');
								transData.setData(new byte[0]);
							}
							string = new String(transData.tobyte());
							writer.write(string+'\n');
							writer.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					String string = null ;
					try {
						while((string = bufferedReader.readLine())!=null){
							System.out.println(string);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	@org.junit.Test
	public void test() {
		TransData transData = new TransData();
		transData.setSrcId("maojun");
		transData.setType(3);
		transData.setData(new byte[0]);
		String string = new String(transData.tobyte());
		System.out.println((int)string.getBytes()[0]);
	}
}
