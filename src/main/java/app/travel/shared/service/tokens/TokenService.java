package app.travel.shared.service.tokens;

import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.common.constant.JwtTokenType;
import app.travel.model.tokens.TokenEntity;
import app.travel.model.tokens.TokenMapper;
import app.travel.model.tokens.TokenRepository;
import app.travel.model.users.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j(topic = "TOKEN-SERVICE")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenService implements ITokenService{

    TokenRepository tokenRepository;

    TokenMapper tokenMapper;

    @Override
    @Transactional
    public TokenEntity saveTokenInclude(String token, JwtTokenType tokenType, UserEntity user) {

        log.info("Saving token include: token {}, token type {}, user id {}", token, tokenType, user.getId());

        TokenEntity tokenEntity = TokenEntity.builder()
                .token(token)
                .tokenType(tokenType)
                .userId(user.getId())
                .build();

        return tokenRepository.save(tokenEntity);
    }

    @Override
    public TokenEntity getTokenById(UUID id) {

        log.info("get token by id {}", id);

        return tokenMapper.findById(id).orElseThrow(
                () -> {

                    log.error("get token by id {} failed", id);

                    return new ErrorHolderException(Error.RESOURCE_NOT_FOUND);
                }
        );
    }


    @Override
    @Transactional
    public void deleteTokenById(UUID tokenId) {

        TokenEntity tokenEntity = getTokenById(tokenId);

        deleteToken(tokenEntity);
    }

    @Override
    @Transactional
    public void deleteToken(TokenEntity token) {

        tokenRepository.delete(token);
    }
}
