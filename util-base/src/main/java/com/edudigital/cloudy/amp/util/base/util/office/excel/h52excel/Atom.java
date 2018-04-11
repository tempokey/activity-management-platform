package com.edudigital.cloudy.amp.util.base.util.office.excel.h52excel;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Atom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Queue<byte[]> byteQueue = new LinkedBlockingQueue<>();

	public Queue<byte[]> getByteQueue() {
		return byteQueue;
	}

	public void setByteQueue(Queue<byte[]> byteQueue) {
		this.byteQueue = byteQueue;
	}

}
