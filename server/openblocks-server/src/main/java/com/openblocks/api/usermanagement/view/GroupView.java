package com.openblocks.api.usermanagement.view;

import java.util.Locale;

import com.openblocks.domain.group.model.Group;
import com.openblocks.sdk.util.LocaleUtils;

import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Mono;

@Getter
@Builder
public class GroupView {

    private String groupId;
    private String groupName;
    private boolean allUsersGroup;
    private boolean isDevGroup;

    public static Mono<GroupView> from(Group group) {
        return Mono.deferContextual(contextView -> {
            Locale locale = LocaleUtils.getLocale(contextView);
            GroupView groupView = GroupView.builder()
                    .groupId(group.getId())
                    .groupName(group.getName(locale))
                    .allUsersGroup(group.isAllUsersGroup())
                    .isDevGroup(group.isDevGroup())
                    .build();
            return Mono.just(groupView);
        });
    }
}
