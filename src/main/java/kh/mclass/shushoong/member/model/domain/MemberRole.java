package kh.mclass.shushoong.member.model.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
	ADMIN("admin"), BUSINESS("business"), USER("user");
	
	private String value;
	
	private MemberRole(String value) {
		this.value = value;
	}
}
