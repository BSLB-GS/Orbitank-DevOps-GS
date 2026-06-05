package br.com.orbitank.dto.Request;
import lombok.Data;

@Data
public class VerifyCodeRequest {
    private String email;
    private String code;
}