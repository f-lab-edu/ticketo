package org.flab.api.domain.event.repository.show;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowRepositoryImpl implements ShowRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
