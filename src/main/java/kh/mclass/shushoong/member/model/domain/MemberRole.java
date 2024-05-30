package kh.mclass.shushoong.member.model.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
	ADMIN("00"), CUSTOMER("01"), BUSINESS("02");
	
	private String value;
	
	private MemberRole(String value) {
		this.value = value;
	}
}
