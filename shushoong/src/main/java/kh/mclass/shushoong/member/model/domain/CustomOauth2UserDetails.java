package kh.mclass.shushoong.member.model.domain;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomOauth2UserDetails {
	private final MemberDto member;
	private Map<String, Object> attributes;
	
	public CustomOauth2UserDetails(MemberDto member, Map<String, Object> attributes) {

        this.member = member;
        this.attributes = attributes;
    }
	
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getName() {
        return null;
    }

    public String getPassword() {
        return member.getUserPwd();
    }

    public String getUsername() {
        return member.getUserId();
    }
    
    public String getUsergrade() {
        return member.getUserGrade();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
