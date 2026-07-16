package com.cts.releasepilot.auth.dto;

/** Response returned after a successful login. */
public class AuthResponseDTO {

    private String token;
    private String role;
    private String userName;
    private String email;
    private Long userID;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, String role, String userName, String email, Long userID) {
        this.token = token;
        this.role = role;
        this.userName = userName;
        this.email = email;
        this.userID = userID;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getUserID() { return userID; }
    public void setUserID(Long userID) { this.userID = userID; }
}
