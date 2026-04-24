package com.trade.tradelicense.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Value object representing the identity of an
 * {@link com.trade.tradelicense.domain.entities.Attachment}.
 *
 * <p>Type-safe wrapper around the raw {@code Long} surrogate key, preventing
 * accidental confusion with other entity identifiers in domain method signatures.
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AttachmentId {

    /** The underlying surrogate key value. */
    private final Long value;
}
