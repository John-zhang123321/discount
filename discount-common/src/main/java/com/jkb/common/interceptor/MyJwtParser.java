//package com.zl.common.interceptor;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.impl.*;
//import io.jsonwebtoken.impl.compression.DefaultCompressionCodecResolver;
//import io.jsonwebtoken.lang.Assert;
//import io.jsonwebtoken.lang.Strings;
//
//import java.util.Map;
//
///**
// * Created by zhangliang on 2019/11/7
// * 默认解析器每次解析都判断token是否过期
// * 该解析器不解析是否过期
// */
//public class MyJwtParser extends DefaultJwtParser {
//
//    private CompressionCodecResolver compressionCodecResolver = new DefaultCompressionCodecResolver();
//
//    @Override
//    public Jwt parse(String jwt) throws ExpiredJwtException, MalformedJwtException, SignatureException {
//
//        Assert.hasText(jwt, "JWT String argument cannot be null or empty.");
//
//        String base64UrlEncodedHeader = null;
//        String base64UrlEncodedPayload = null;
//        String base64UrlEncodedDigest = null;
//
//        int delimiterCount = 0;
//
//        StringBuilder sb = new StringBuilder(128);
//
//        for (char c : jwt.toCharArray()) {
//
//            if (c == SEPARATOR_CHAR) {
//
//                CharSequence tokenSeq = Strings.clean(sb);
//                String token = tokenSeq!=null?tokenSeq.toString():null;
//
//                if (delimiterCount == 0) {
//                    base64UrlEncodedHeader = token;
//                } else if (delimiterCount == 1) {
//                    base64UrlEncodedPayload = token;
//                }
//
//                delimiterCount++;
//                sb.setLength(0);
//            } else {
//                sb.append(c);
//            }
//        }
//
//        if (delimiterCount != 2) {
//            String msg = "JWT strings must contain exactly 2 period characters. Found: " + delimiterCount;
//            throw new MalformedJwtException(msg);
//        }
//        if (sb.length() > 0) {
//            base64UrlEncodedDigest = sb.toString();
//        }
//
//        if (base64UrlEncodedPayload == null) {
//            throw new MalformedJwtException("JWT string '" + jwt + "' is missing a body/payload.");
//        }
//
//        // =============== Header =================
//        Header header = null;
//
//        CompressionCodec compressionCodec = null;
//
//        if (base64UrlEncodedHeader != null) {
//            String origValue = TextCodec.BASE64URL.decodeToString(base64UrlEncodedHeader);
//            Map<String, Object> m = readValue(origValue);
//
//            if (base64UrlEncodedDigest != null) {
//                header = new DefaultJwsHeader(m);
//            } else {
//                header = new DefaultHeader(m);
//            }
//
//            compressionCodec = compressionCodecResolver.resolveCompressionCodec(header);
//        }
//
//        // =============== Body =================
//        String payload;
//        if (compressionCodec != null) {
//            byte[] decompressed = compressionCodec.decompress(TextCodec.BASE64URL.decode(base64UrlEncodedPayload));
//            payload = new String(decompressed, Strings.UTF_8);
//        } else {
//            payload = TextCodec.BASE64URL.decodeToString(base64UrlEncodedPayload);
//        }
//
//        Claims claims = null;
//
//        if (payload.charAt(0) == '{' && payload.charAt(payload.length() - 1) == '}') { //likely to be json, parse it:
//            Map<String, Object> claimsMap = readValue(payload);
//            claims = new DefaultClaims(claimsMap);
//        }
//
//
//        Object body = claims != null ? claims : payload;
//
//        if (base64UrlEncodedDigest != null) {
//            return new DefaultJws<Object>((JwsHeader) header, body, base64UrlEncodedDigest);
//        } else {
//            return new DefaultJwt<Object>(header, body);
//        }
//    }
//
//
//    @Override
//    public <T> T parse(String compact, JwtHandler<T> handler)
//            throws ExpiredJwtException, MalformedJwtException, SignatureException {
//        Assert.notNull(handler, "JwtHandler argument cannot be null.");
//        Assert.hasText(compact, "JWT String argument cannot be null or empty.");
//
//        Jwt jwt = parse(compact);
//
//        if (jwt instanceof Jws) {
//            Jws jws = (Jws) jwt;
//            Object body = jws.getBody();
//            if (body instanceof Claims) {
//                return handler.onClaimsJws((Jws<Claims>) jws);
//            } else {
//                return handler.onPlaintextJws((Jws<String>) jws);
//            }
//        } else {
//            Object body = jwt.getBody();
//            if (body instanceof Claims) {
//                return handler.onClaimsJwt((Jwt<Header, Claims>) jwt);
//            } else {
//                return handler.onPlaintextJwt((Jwt<Header, String>) jwt);
//            }
//        }
//    }
//}
