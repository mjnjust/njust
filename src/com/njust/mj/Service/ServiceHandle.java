package com.njust.mj.Service;


import com.njust.mj.Bean.TransData;
import com.njust.mj.Controll.ServiceMain;

public class ServiceHandle {
	
	public static void transString(TransData transData) {
		 int index = ServiceMain.id2index.get(transData.getdesId());
		 /*
		  * 数据持久化逻辑
		  */
		 System.out.println("do transString");
		 ServiceMain.clientThreads[index].write(transData);
	}
	public static void transFile(TransData transData) {
		int index = ServiceMain.id2index.get(transData.getdesId());
		/*
		 * 数据持久化逻辑
		 */
		System.out.println("do transFile");
		ServiceMain.clientThreads[index].write(transData);
	}
	public static void transImage(TransData transData) {
		int index = ServiceMain.id2index.get(transData.getdesId());
		/*
		 * 数据持久化逻辑
		 */
		System.out.println("do transImage");
		ServiceMain.clientThreads[index].write(transData);
	}
	
}
