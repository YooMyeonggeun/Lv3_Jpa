package com.sparta.lv1_test.util;

import com.sparta.lv1_test.entity.User;
import com.sparta.lv1_test.jwt.JwtUtil;
import com.sparta.lv1_test.repository.UserRepsotiry;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TockenUtil {

    private final UserRepsotiry userRepsotiry;
    private final JwtUtil jwtUtil;
    public User tockencheck(HttpServletRequest request){
        //토큰값 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims = null;

        //토큰값이 null이 아니고 유효한 토큰값인지 확인
        if(token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else{
                throw new IllegalArgumentException("Token Error");
            }
        }
        
        //토큰으로 DB조회
        User user = userRepsotiry.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
        );
        return user; // 토큰안에 사용자 정보 전달
    }
}
