package com.nadeul.ndj.dto;

import java.util.Map;

import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.model.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
    private String name;
    private String email;
    private String profileUrl;
    
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileUrl) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
    }
    
    public static OAuthAttributes of(String socialName, String userNameAttributeName, Map<String, Object> attributes){
        // 카카오         
        if("kakao".equals(socialName)){
            return ofKakao("id", attributes);
        }
        
        return null;
    }
    
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .profileUrl((String) kakaoAccount.get("thumbnail_image_url"))
                .nameAttributeKey(userNameAttributeName)
                .attributes(attributes)
                .build();
    }
    
    public Member toEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .profileUrl(profileUrl)
                .role(Role.USER)
                .build();
    }
    
    @Override
    public String toString() {
        return "OAuthAttributes{" +
                "attributes=" + attributes +
                ", nameAttributeKey='" + nameAttributeKey + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}