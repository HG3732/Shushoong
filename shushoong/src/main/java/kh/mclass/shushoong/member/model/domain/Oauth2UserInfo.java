package kh.mclass.shushoong.member.model.domain;

public interface Oauth2UserInfo {
	String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
