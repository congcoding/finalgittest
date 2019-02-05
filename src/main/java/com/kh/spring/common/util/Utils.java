package com.kh.spring.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static String getPageBar(int totalContents, int cPage, int numPerPage, String loc) {
		
		int totalContent = totalContents;
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		int pageBarSize = 5;
		int startPage = ((cPage-1)/pageBarSize) * pageBarSize + 1;
		int endPage = startPage + pageBarSize -1;
		int pageNo = startPage;
		
		String pageBar = "<nav><ul class='pagination justify-content-center'>";
		
		//[이전]section
		if(pageNo == 1) {
			pageBar += "<li class='page-item disabled'><a class='page-link'>이전</a></li>";
		}
		else {
			pageBar += "<li class='page-item'><a class='page-link' href='"+loc+"?cPage="+(pageNo-1)+"'>이전</a></li>";
		}
		
		//[페이지]section
		while(pageNo<=endPage && pageNo<=totalPage) {
			if(cPage == pageNo) {
				pageBar += "<li class='page-item active'><a class='page-link'>"+pageNo+"</a></li>";
			}
			else {
				pageBar += "<li class='page-item'><a class='page-link' href='"+loc+"?cPage="+pageNo+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
		}
		
		//[다음]section
		if(pageNo > totalPage) {
			pageBar += "<li class='page-item disabled'><a class='page-link'>다음</a></li>";
		}
		else {
			pageBar += "<li class='page-item'><a class='page-link' href='"+loc+"?cPage="+pageNo+"'>다음</a></li>";
		}
		
		pageBar +="</ul></nav>";
		
		return pageBar;
	}

	public static String getRenamedFileName(String fname) {
		//확장자 분리
		String ext = fname.substring(fname.lastIndexOf(".")+1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD_HHmmssSSS");
		int rndNum = (int)(Math.random()*1000);
		return sdf.format(new Date())+"_"+rndNum+"."+ext;
	}
}
