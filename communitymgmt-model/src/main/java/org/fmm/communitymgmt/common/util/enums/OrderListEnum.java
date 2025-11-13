package org.fmm.communitymgmt.common.util.enums;

import java.util.stream.Stream;

public enum OrderListEnum {
	RESPONSIBLES(100,"Responsibles"),
	MARRIAGES(200, "Marriages"),
	SINGLES(300, "Singles"),
	OTHERS(400, "Others");
	
	private final Integer id;
	private final String intent;
	
	private OrderListEnum(Integer id, String intent) {
		this.id = id;
		this.intent = intent;
	}
	
	public Integer getId() {
		return id;
	}

	public String getMembership() {
		return intent;
	}
	
	public static OrderListEnum of(int id) {
		return Stream.of(OrderListEnum.values())
				.filter(o->o.getId() == id)
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
	
}
