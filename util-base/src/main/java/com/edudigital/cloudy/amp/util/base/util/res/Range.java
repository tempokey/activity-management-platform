package com.edudigital.cloudy.amp.util.base.util.res;

public class Range {
	long start;
	long end;
	long length;
	long total;

	public Range(long start, long end, long total) {
		this.start = start;
		this.end = end;
		this.length = end - start + 1;
		this.total = total;
	}
}
