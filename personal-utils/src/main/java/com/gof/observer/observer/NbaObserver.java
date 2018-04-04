package com.gof.observer.observer;

import com.gof.observer.notice.SubjectService;

public class NbaObserver extends Observer {

	public NbaObserver(String name, SubjectService subjectService) {
		super(name, subjectService);
	}

	@Override
	public void update() {
		System.out.printf("%s,关闭NBA视频，继续工作,%s%n",this.subjectService.get(),this.name);
	}

}
