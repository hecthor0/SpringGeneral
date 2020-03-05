package com.spring.legacy;

import java.util.Comparator;

import com.spring.bean.PersonaTarea;

public class ArrangeMm implements Comparator<PersonaTarea>{

	@Override
	public int compare(PersonaTarea pt1, PersonaTarea pt2) {
		if(pt1.getEdad() > pt2.getEdad()) {
			return -1;
		}
		
		if(pt1.getEdad() < pt2.getEdad()) {
			return 1;
		}
		
		return 0;
	}

}
