package com.inmaytide.orbit.captcha.handler;

import com.inmaytide.orbit.captcha.service.CaptchaService;
import com.inmaytide.orbit.commons.id.UUIDGenerator;
import com.inmaytide.orbit.util.Assert;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.inmaytide.orbit.constant.Constants.HEADER_NAME_CAPTCHA_NAME;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class CaptchaHandler {

    @Autowired
    private CaptchaService service;

    private String getCacheName(ServerRequest request) {
        List<String> captchaNames = request.headers().header(HEADER_NAME_CAPTCHA_NAME);
        Assert.nonEmpty(captchaNames, "Bad captcha name.");
        return captchaNames.get(0);
    }

    @NonNull
    public Mono<ServerResponse> getCaptcha(@SuppressWarnings("unused") ServerRequest request) {
        String cacheName = UUIDGenerator.generate();
        Resource resource = service.generateCaptcha(cacheName);
        try {
            String image = Base64.encodeBase64String(FileUtils.readFileToByteArray(resource.getFile()));
            return ok().body(Mono.just(Map.of("image", image, "captchaName", cacheName)), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public Mono<ServerResponse> validation(ServerRequest request) {
        String captcha = request.pathVariable("captcha");
        Boolean isValid = service.validation(captcha, getCacheName(request));
        return ok().body(Mono.just(Map.of("isValid", isValid)), Map.class);
    }

}
