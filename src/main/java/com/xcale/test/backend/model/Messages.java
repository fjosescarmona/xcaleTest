package com.xcale.test.backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("messagges")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Messages {

    @Id
    @Column("id")
    private long id;

    @Column("groupId")
    private String groupId;

    @Column("message")
    private String message;
}
