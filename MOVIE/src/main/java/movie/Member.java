/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movie;

/**
 *
 * @author 회원가입 빌더
 */
public class Member {

    private String id;
    private String pw;
    private String name;
    private String birth;
    private String phone;

    public Member(MemberBuilder builder) {
        this.id = builder.id;
        this.pw = builder.pw;
        this.name = builder.name;
        this.birth = builder.birth;
        this.phone = builder.phone;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return id + "," + pw + "," + name + "," + birth + "," + phone;
    }

    public static class MemberBuilder {

        private String id;
        private String pw;
        private String name;
        private String birth;
        private String phone;

        public MemberBuilder setId(String id, String pw) {
            this.id = id;
            this.pw = pw;
            return this;
        }

        public MemberBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public MemberBuilder setBirth(String birth) {
            this.birth = birth;
            return this;
        }

        public MemberBuilder setPhoneNumber(String phone) {
            this.phone = phone;
            return this;
        }

        public Member build() {
            return new Member(this);
        }

    }
}
